package com.app.tmdb;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.errors.BusinessException;
import com.app.tmdb.errors.TmdbError;
import com.app.tmdb.models.responses.MovieDetailsResponse;
import com.app.tmdb.models.request.MovieDetailsRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.SimulationSource;
import io.specto.hoverfly.junit5.HoverflyExtension;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.Proxy;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import static io.specto.hoverfly.junit.dsl.HoverflyDsl.*;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static org.assertj.core.api.Assertions.*;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.notFound;

@ExtendWith(HoverflyExtension.class)
class TmdbClientTest {

    private TmdbClient tmdbClient;

    @BeforeEach
    void setUp(Hoverfly hoverfly) {

        hoverfly.simulate(
                SimulationSource.dsl(

                        service("api.themoviedb.org")
                                .get("/3/movie/550/lists")
                                .queryParam("page", "1")
                                .queryParam("language", "en-US")
                                .willReturn(success(
                                        loadJson("/hoverfly/movie-lists-success.json"),
                                        "application/json"
                                )),

                        service("api.themoviedb.org")
                                .get("/3/movie/550")
                                .queryParam("language", "en-US")
                                .willReturn(success(
                                        loadJson("/hoverfly/movie-details-success.json"),
                                        "application/json"
                                )),

                        service("api.themoviedb.org")
                                .get("/3/movie/1")
                                .queryParam("language", "en-US")
                                .willReturn(
                                        notFound()
                                                .body(loadJson("/hoverfly/tmdb-error-404.json"))
                                                .header("Content-Type", "application/json")
                                )
                )
        );

        OkHttpClient client = buildUnsafeClientWithHoverfly(hoverfly);


        tmdbClient = new TmdbClient(client, new ObjectMapper());
    }

    private String loadJson(String fileName) {
        try (InputStream is = getClass().getResourceAsStream(fileName)) {
            return new String(is.readAllBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load JSON file: " + fileName, e);
        }
    }

    @Test
    void testGetMovieDetailsSuccess() {
        MovieDetailsRequest request = new MovieDetailsRequest();
        request.setMovieId(550L);
        request.setLanguage("en-US");

        MovieDetailsResponse response = tmdbClient.getMovieDetails(request);

        assertThat(response.getTitle()).isEqualTo("Fight Club");
    }

    @Test
    void testGetMovieDetailsNotFound() {
        MovieDetailsRequest request = new MovieDetailsRequest();
        request.setMovieId(1L);
        request.setLanguage("en-US");

        assertThatThrownBy(() -> tmdbClient.getMovieDetails(request))
                .isInstanceOf(BusinessException.class)
                .hasMessage(TmdbError.MOVIE_NOT_FOUND.getMessage());
    }

    private static OkHttpClient buildUnsafeClientWithHoverfly(Hoverfly hoverfly) {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            return new OkHttpClient.Builder()
                    .sslSocketFactory(
                            sslSocketFactory,
                            (X509TrustManager) trustAllCerts[0]
                    )
                    .hostnameVerifier((hostname, session) -> true)
                    .proxy(new Proxy(
                            Proxy.Type.HTTP,
                            new InetSocketAddress(
                                    "localhost",
                                    hoverfly.getHoverflyConfig().getProxyPort()
                            )
                    ))
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

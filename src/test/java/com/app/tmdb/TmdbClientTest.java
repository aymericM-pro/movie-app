package com.app.tmdb;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.errors.BusinessException;
import com.app.tmdb.errors.TmdbError;
import com.app.tmdb.models.responses.MovieDetailsResponse;
import com.app.tmdb.requests.MovieDetailsRequest;
import com.app.tmdb.requests.MovieListsRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit.core.HoverflyConfig;
import io.specto.hoverfly.junit.core.HoverflyMode;
import io.specto.hoverfly.junit.core.SimulationSource;
import io.specto.hoverfly.junit.dsl.ResponseCreators;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.*;

import java.io.InputStream;

import static io.specto.hoverfly.junit.dsl.HoverflyDsl.*;
import static org.assertj.core.api.Assertions.*;

class TmdbClientTest {

    private static Hoverfly hoverfly;
    private static TmdbClient tmdbClient;

    private static OkHttpClient okHttpClientWithHoverflyProxy() {
        return new OkHttpClient.Builder()
                .proxy(new java.net.Proxy(
                        java.net.Proxy.Type.HTTP,
                        new java.net.InetSocketAddress(
                                "localhost",
                                hoverfly.getHoverflyConfig().getProxyPort()
                        )
                ))
                .build();
    }


    @BeforeAll
    static void setUp() {

        hoverfly = new Hoverfly(
                HoverflyConfig.localConfigs(),
                HoverflyMode.SIMULATE
        );

        hoverfly.start();

        hoverfly.simulate(
                SimulationSource.dsl(

                        service("api.themoviedb.org")
                                .get("/3/movie/550/lists")
                                .queryParam("page", "1")
                                .queryParam("language", "en-US")
                                .willReturn(
                                        ResponseCreators.success(
                                                loadJson("/hoverfly/movie-lists-success.json"),
                                                "application/json"
                                        )
                                ),

                        service("api.themoviedb.org")
                                .get("/3/movie/550")
                                .queryParam("language", "en-US")
                                .willReturn(
                                        ResponseCreators.success(
                                                loadJson("/hoverfly/movie-details-success.json"),
                                                "application/json"
                                        )
                                ),

                        service("api.themoviedb.org")
                                .get("/3/movie/1")
                                .queryParam("language", "en-US")
                                .willReturn(
                                        ResponseCreators.notFound()
                                                .body(loadJson("/hoverfly/tmdb-error-404.json"))
                                                .header("Content-Type", "application/json")
                                )
                )
        );

        tmdbClient = new TmdbClient(
                okHttpClientWithHoverflyProxy(),
                new ObjectMapper()
        );
    }


    @AfterAll
    static void tearDown() {
        hoverfly.close();
    }

    @Test
    void testGetMovieListsSuccess() {

        MovieListsRequest request = new MovieListsRequest();
        request.setMovieId(550L);
        request.setPage(1);
        request.setLanguage("en-US");

        var response = tmdbClient.getMovieLists(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(550);
        assertThat(response.getResults()).isNotEmpty();
    }

    @Test
    void testGetMovieDetailsSuccess() {

        MovieDetailsRequest request = new MovieDetailsRequest();
        request.setMovieId(550L);
        request.setLanguage("en-US");

        MovieDetailsResponse response =
                tmdbClient.getMovieDetails(request);

        assertThat(response.getTitle()).isEqualTo("Fight Club");
        assertThat(response.getRuntime()).isEqualTo(139);
    }

    @Test
    void testGetMovieDetailsNotFound() {

        MovieDetailsRequest request = new MovieDetailsRequest();
        request.setMovieId(1L);
        request.setLanguage("en-US");

        assertThatThrownBy(() ->
                tmdbClient.getMovieDetails(request)
        )
                .isInstanceOf(BusinessException.class)
                .hasMessage(TmdbError.MOVIE_NOT_FOUND.getMessage());
    }

    private static String loadJson(String path) {
        try (InputStream is =
                     TmdbClientTest.class.getResourceAsStream(path)) {

            if (is == null) {
                throw new IllegalStateException("File not found: " + path);
            }
            return new String(is.readAllBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

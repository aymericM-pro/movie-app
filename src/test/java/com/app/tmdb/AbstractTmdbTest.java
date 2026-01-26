package com.app.tmdb;

import com.app.tmdb.TmdbProperties;
import io.specto.hoverfly.junit5.HoverflyExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import(AbstractTmdbTest.TmdbTestConfig.class)
public abstract class AbstractTmdbTest {

    @TestConfiguration
    static class TmdbTestConfig {

        @Bean
        public TmdbProperties tmdbProperties() {
            TmdbProperties props = new TmdbProperties();
            props.setBaseUrl("https://api.themoviedb.org/3");
            return props;
        }
    }
}

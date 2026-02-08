package com.app.tmdb.modules.collections.usecases;

import com.app.tmdb.models.enums.MovieGenre;
import com.app.tmdb.models.responses.CollectionResponse;
import com.app.tmdb.models.responses.MovieDetailsResponse;
import com.app.tmdb.models.responses.collections.*;
import com.app.tmdb.models.responses.collections.CollectionMovieDetails;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public final class CollectionAggregator {

    private CollectionAggregator() {}

    public static CollectionDetailsResponse aggregate(
            CollectionResponse collection,
            List<MovieDetailsResponse> movies
    ) {

        CollectionDetailsResponse response = new CollectionDetailsResponse();

        List<CollectionMovieDetails> movieDetails = mapMovies(collection, movies);
        response.setMovies(movieDetails);

        response.setId(collection.getId());
        response.setName(collection.getName());
        response.setOverview(collection.getOverview());
        response.setPosterPath(collection.getPosterPath());

        response.setBackdropPath(
                collection.getBackdropPath() != null
                        ? collection.getBackdropPath()
                        : movieDetails.stream()
                        .map(CollectionMovieDetails::getBackdropPath)
                        .filter(Objects::nonNull)
                        .findFirst()
                        .orElse(null)
        );

        response.setStats(buildStats(movieDetails));

        response.setMeta(buildMeta(movieDetails));

        return response;
    }

    private static List<CollectionMovieDetails> mapMovies(
            CollectionResponse collection,
            List<MovieDetailsResponse> movies
    ) {

        Map<Long, Integer> orderByMovieId = new HashMap<>();
        for (int i = 0; i < collection.getParts().size(); i++) {
            orderByMovieId.put(collection.getParts().get(i).getId(), i + 1);
        }

        return movies.stream()
                .map(m -> toMovie(m, orderByMovieId.get(m.getId())))
                .sorted(Comparator.comparing(CollectionMovieDetails::getOrder))
                .toList();
    }

    private static CollectionMovieDetails toMovie(
            MovieDetailsResponse m,
            Integer order
    ) {
        CollectionMovieDetails d = new CollectionMovieDetails();

        d.setId(m.getId());
        d.setTitle(m.getTitle());
        d.setOriginalTitle(m.getOriginalTitle());
        d.setOverview(m.getOverview());

        d.setPosterPath(m.getPosterPath());
        d.setBackdropPath(m.getBackdropPath());

        d.setReleaseDate(
                m.getReleaseDate() != null
                        ? LocalDate.parse(m.getReleaseDate())
                        : null
        );

        d.setVoteAverage(m.getVoteAverage());
        d.setVoteCount(m.getVoteCount());

        d.setBudget(m.getBudget());
        d.setRevenue(m.getRevenue());
        d.setRuntime(m.getRuntime());

        d.setGenres(
                m.getGenres() == null
                        ? List.of()
                        : m.getGenres().stream()
                        .map(g -> MovieGenre.fromId(g.getId()))
                        .flatMap(Optional::stream)
                        .map(Enum::name)
                        .toList()
        );


        d.setProductionCountries(
                m.getProductionCountries() == null ? List.of()
                        : m.getProductionCountries().stream().map(MovieDetailsResponse.ProductionCountryRef::getName).toList()
        );

        d.setSpokenLanguages(
                m.getSpokenLanguages() == null ? List.of()
                        : m.getSpokenLanguages().stream().map(MovieDetailsResponse.SpokenLanguageRef::getEnglishName).toList()
        );

        d.setOrder(order);

        return d;
    }

    private static CollectionStats buildStats(List<CollectionMovieDetails> movies) {

        CollectionStats stats = new CollectionStats();

        long totalBudget = movies.stream()
                .map(CollectionMovieDetails::getBudget)
                .filter(Objects::nonNull)
                .mapToLong(Long::longValue)
                .sum();

        long totalRevenue = movies.stream()
                .map(CollectionMovieDetails::getRevenue)
                .filter(Objects::nonNull)
                .mapToLong(Long::longValue)
                .sum();

        int totalRuntime = movies.stream()
                .map(CollectionMovieDetails::getRuntime)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();

        BigDecimal avgRating = movies.stream()
                .map(CollectionMovieDetails::getVoteAverage)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(
                        BigDecimal.valueOf(
                                movies.stream().filter(m -> m.getVoteAverage() != null).count()
                        ),
                        2,
                        RoundingMode.HALF_UP
                );

        stats.setTotalBudget(totalBudget);
        stats.setTotalRevenue(totalRevenue);
        stats.setProfit(totalRevenue - totalBudget);

        stats.setAverageRating(avgRating);

        stats.setTotalRuntimeMinutes(totalRuntime);
        stats.setTotalRuntimeFormatted(formatRuntime(totalRuntime));

        stats.setMovieCount(movies.size());

        return stats;
    }

    private static String formatRuntime(int minutes) {
        int h = minutes / 60;
        int m = minutes % 60;
        return h + "h " + m + "m";
    }

    // ----------------------------------------------------------------
    // Meta
    // ----------------------------------------------------------------

    private static CollectionMeta buildMeta(List<CollectionMovieDetails> movies) {

        CollectionMeta meta = new CollectionMeta();

        meta.setGenres(count(movies, CollectionMovieDetails::getGenres));
        meta.setLanguages(count(movies, CollectionMovieDetails::getSpokenLanguages));
        meta.setProductionCountries(count(movies, CollectionMovieDetails::getProductionCountries));

        // productionCompanies pas encore dans ton MovieDetailsResponse
        meta.setProductionCompanies(Map.of());

        return meta;
    }

    private static Map<String, Integer> count(
            List<CollectionMovieDetails> movies,
            java.util.function.Function<CollectionMovieDetails, List<String>> extractor
    ) {
        return movies.stream()
                .flatMap(m -> extractor.apply(m).stream())
                .collect(Collectors.toMap(
                        v -> v,
                        v -> 1,
                        Integer::sum,
                        LinkedHashMap::new
                ));
    }
}

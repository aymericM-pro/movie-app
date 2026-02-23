package com.app.tmdb.modules.search.requests;

import com.app.tmdb.models.request.PagedRequest;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import java.util.ArrayList;


@Getter
@Setter
public class DiscoverMoviesRequest extends PagedRequest {

    private List<Integer> genres = new ArrayList<>();

    private BigDecimal voteAverageMin;
    private BigDecimal voteAverageMax;
    private Integer voteCountMin;
    private Integer runtimeMin;
    private Integer runtimeMax;
    private String sortBy;

    public static DiscoverMoviesRequest of(
            List<Integer> genres, BigDecimal voteAverageMin, BigDecimal voteAverageMax,
            Integer voteCountMin, Integer runtimeMin, Integer runtimeMax,
            String sortBy, String language, String region, Integer page) {
        DiscoverMoviesRequest request = new DiscoverMoviesRequest();
        request.setGenres(genres != null ? genres : new ArrayList<>());
        request.setVoteAverageMin(voteAverageMin);
        request.setVoteAverageMax(voteAverageMax);
        request.setVoteCountMin(voteCountMin);
        request.setRuntimeMin(runtimeMin);
        request.setRuntimeMax(runtimeMax);
        request.setSortBy(sortBy);
        request.setLanguage(language);
        request.setRegion(region);
        request.setPage(page);
        return request;
    }

    @Override
    protected void collectViolations(List<String> v) {
        super.collectViolations(v);

        if (voteAverageMin != null && voteAverageMax != null &&
                voteAverageMin.compareTo(voteAverageMax) > 0) {
            v.add("voteAverageMin must be <= voteAverageMax");
        }

        checkInt(v, runtimeMin, "runtimeMin", true, 0, null);
        checkInt(v, runtimeMax, "runtimeMax", true, 0, null);

        if (runtimeMin != null && runtimeMax != null && runtimeMin > runtimeMax) {
            v.add("runtimeMin must be <= runtimeMax");
        }

        checkInt(v, voteCountMin, "voteCountMin", true, 0, null);
        checkString(v, sortBy, "sortBy", true, null, null);
    }
}

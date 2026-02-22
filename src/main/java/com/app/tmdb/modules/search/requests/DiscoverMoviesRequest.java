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

    @Override
    protected void validate() {

        if (genres == null) {
            genres = new ArrayList<>();
        }

        if (voteAverageMin != null && voteAverageMax != null &&
                voteAverageMin.compareTo(voteAverageMax) > 0) {
            fail("voteAverageMin must be <= voteAverageMax");
        }

        checkInt(runtimeMin, "runtimeMin", true, 0, null);
        checkInt(runtimeMax, "runtimeMax", true, 0, null);

        if (runtimeMin != null && runtimeMax != null && runtimeMin > runtimeMax) {
            fail("runtimeMin must be <= runtimeMax");
        }

        checkInt(voteCountMin, "voteCountMin", true, 0, null);
        checkString(sortBy, "sortBy", true, null, null);
    }

}

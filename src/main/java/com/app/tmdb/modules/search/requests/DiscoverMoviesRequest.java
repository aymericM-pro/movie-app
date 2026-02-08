package com.app.tmdb.modules.search.requests;

import com.app.tmdb.models.request.ServiceParams;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import java.util.ArrayList;


@Getter
@Setter
public class DiscoverMoviesRequest extends ServiceParams {

    private List<Integer> genres = new ArrayList<>();

    private BigDecimal voteAverageMin;
    private BigDecimal voteAverageMax;
    private Integer voteCountMin;
    private Integer runtimeMin;
    private Integer runtimeMax;
    private String sortBy;

    private String language = "fr-FR";
    private String region = "FR";
    private Integer page = 1;

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
        checkInt(page, "page", true, 1, null);

        checkString(sortBy, "sortBy", true, null, null);
        checkString(language, "language", true, 2, 10);
        checkString(region, "region", true, 2, 5);
    }

}

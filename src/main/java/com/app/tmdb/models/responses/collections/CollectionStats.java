package com.app.tmdb.models.responses.collections;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CollectionStats {

    private Long totalBudget;
    private Long totalRevenue;
    private Long profit;

    private BigDecimal averageRating;

    private Integer totalRuntimeMinutes;
    private String totalRuntimeFormatted;

    private Integer movieCount;
}

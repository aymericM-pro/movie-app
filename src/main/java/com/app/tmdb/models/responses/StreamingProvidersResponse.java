package com.app.tmdb.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingProvidersResponse {

    private List<Provider> results;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Provider {

        @JsonProperty("provider_id")
        private Long providerId;

        @JsonProperty("provider_name")
        private String providerName;

        @JsonProperty("logo_path")
        private String logoPath;

        @JsonProperty("display_priority")
        private Integer displayPriority;
    }
}

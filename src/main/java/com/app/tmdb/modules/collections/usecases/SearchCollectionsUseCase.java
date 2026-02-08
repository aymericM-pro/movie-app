package com.app.tmdb.modules.collections.usecases;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.modules.collections.mapper.CollectionMapper;
import com.app.tmdb.modules.collections.request.SearchCollectionsRequest;
import com.app.tmdb.modules.collections.responses.CollectionSearchResponse;
import com.app.tmdb.modules.collections.responses.PopularCollectionResponse;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchCollectionsUseCase
        extends UseCase<SearchCollectionsRequest, List<PopularCollectionResponse>> {

    private final TmdbClient tmdbClient;

    @Override
    protected List<PopularCollectionResponse> doExecute(
            SearchCollectionsRequest params
    ) {
        CollectionSearchResponse response =
                tmdbClient.searchCollections(
                        params.getQuery(),
                        params.getLanguage()
                );

        if (response.getResults() == null || response.getResults().isEmpty()) {
            return List.of();
        }

        return response.getResults().stream()
                .map(CollectionMapper::fromSearch)
                .toList();
    }
}

package com.app.tmdb.modules.collections.usecases;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.modules.collections.request.GetPopularCollectionsRequest;
import com.app.tmdb.modules.collections.responses.PopularCollectionResponse;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetPopularCollectionsUseCase
        extends UseCase<GetPopularCollectionsRequest, List<PopularCollectionResponse>> {

    private final TmdbClient tmdbClient;

    /**
     * Collections mises en avant (curated list)
     */
    private static final List<Long> POPULAR_COLLECTION_IDS = List.of(
            10L,     // Star Wars
            556L,    // Spider-Man
            87096L,  // Marvel Cinematic Universe
            645L,    // James Bond
            2344L,   // The Matrix
            131295L, // Fast & Furious
            9485L    // The Lord of the Rings
    );

    @Override
    protected List<PopularCollectionResponse> doExecute(
            GetPopularCollectionsRequest params
    ) {
        return POPULAR_COLLECTION_IDS.stream()
                .map(id ->
                        tmdbClient.getCollectionById(
                                id,
                                params.getLanguage()
                        )
                )
                .map(PopularCollectionResponse::from)
                .toList();
    }
}

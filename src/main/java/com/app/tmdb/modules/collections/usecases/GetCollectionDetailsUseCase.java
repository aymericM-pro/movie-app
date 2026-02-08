package com.app.tmdb.modules.collections.usecases;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.request.CollectionByIdRequest;
import com.app.tmdb.models.request.MovieDetailsRequest;
import com.app.tmdb.models.responses.CollectionResponse;
import com.app.tmdb.models.responses.MovieDetailsResponse;
import com.app.tmdb.models.responses.collections.CollectionDetailsResponse;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetCollectionDetailsUseCase
        extends UseCase<CollectionByIdRequest, CollectionDetailsResponse> {

    private final TmdbClient client;

    @Override
    protected CollectionDetailsResponse doExecute(CollectionByIdRequest request) {

        CollectionResponse collection =
                client.getCollectionById(request.getCollectionId(), request.getLanguage());

        List<MovieDetailsResponse> movies =
                collection.getParts().stream()
                        .map(p -> {
                            MovieDetailsRequest movieRequest = new MovieDetailsRequest();
                            movieRequest.setMovieId(p.getId());
                            movieRequest.setLanguage(request.getLanguage());
                            return client.getMovieDetails(movieRequest);
                        })
                        .toList();

        return CollectionAggregator.aggregate(collection, movies);
    }
}

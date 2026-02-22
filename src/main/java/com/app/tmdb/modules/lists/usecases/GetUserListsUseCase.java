package com.app.tmdb.modules.lists.usecases;

import com.app.tmdb.modules.lists.repository.MovieListRepository;
import com.app.tmdb.modules.lists.request.GetUserListsRequest;
import com.app.tmdb.modules.lists.response.MovieListResponse;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetUserListsUseCase extends UseCase<GetUserListsRequest, List<MovieListResponse>> {

    private final MovieListRepository movieListRepository;

    @Override
    protected List<MovieListResponse> doExecute(GetUserListsRequest params) {
        return movieListRepository.findAllByUserEmail(params.getUserEmail())
                .stream()
                .map(MovieListResponse::fromWithoutItems)
                .toList();
    }
}

package com.app.tmdb.modules.lists.usecases;

import com.app.tmdb.errors.BusinessException;
import com.app.tmdb.errors.MovieListError;
import com.app.tmdb.modules.lists.repository.MovieListRepository;
import com.app.tmdb.modules.lists.request.ListIdRequest;
import com.app.tmdb.modules.lists.response.MovieListResponse;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetMovieListUseCase extends UseCase<ListIdRequest, MovieListResponse> {

    private final MovieListRepository movieListRepository;

    @Override
    @Transactional
    protected MovieListResponse doExecute(ListIdRequest params) {
        var list = movieListRepository.findByIdAndUserEmail(params.getListId(), params.getUserEmail())
                .orElseThrow(() -> new BusinessException(MovieListError.LIST_NOT_FOUND));

        return MovieListResponse.from(list);
    }
}

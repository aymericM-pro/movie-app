package com.app.tmdb.modules.lists.usecases;

import com.app.tmdb.errors.BusinessException;
import com.app.tmdb.errors.MovieListError;
import com.app.tmdb.modules.lists.repository.MovieListItemRepository;
import com.app.tmdb.modules.lists.repository.MovieListRepository;
import com.app.tmdb.modules.lists.request.ListIdRequest;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemoveMovieFromListUseCase extends UseCase<ListIdRequest, Void> {

    private final MovieListRepository movieListRepository;
    private final MovieListItemRepository movieListItemRepository;

    @Override
    protected Void doExecute(ListIdRequest params) {
        movieListRepository.findByIdAndUserEmail(params.getListId(), params.getUserEmail())
                .orElseThrow(() -> new BusinessException(MovieListError.LIST_NOT_FOUND));

        var item = movieListItemRepository.findByMovieListIdAndTmdbMovieId(
                        params.getListId(), params.getTmdbMovieId())
                .orElseThrow(() -> new BusinessException(MovieListError.MOVIE_NOT_IN_LIST));

        movieListItemRepository.delete(item);
        return null;
    }
}

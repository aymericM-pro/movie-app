package com.app.tmdb.modules.lists.usecases;

import com.app.tmdb.errors.BusinessException;
import com.app.tmdb.errors.MovieListError;
import com.app.tmdb.modules.lists.repository.MovieListRepository;
import com.app.tmdb.modules.lists.request.ListIdRequest;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteMovieListUseCase extends UseCase<ListIdRequest, Void> {

    private final MovieListRepository movieListRepository;

    @Override
    protected Void doExecute(ListIdRequest params) {
        var list = movieListRepository.findByIdAndUserEmail(params.getListId(), params.getUserEmail())
                .orElseThrow(() -> new BusinessException(MovieListError.LIST_NOT_FOUND));

        movieListRepository.delete(list);
        return null;
    }
}

package com.app.tmdb.modules.lists.usecases;

import com.app.tmdb.errors.BusinessException;
import com.app.tmdb.errors.MovieListError;
import com.app.tmdb.modules.lists.entity.MovieListItemEntity;
import com.app.tmdb.modules.lists.repository.MovieListItemRepository;
import com.app.tmdb.modules.lists.repository.MovieListRepository;
import com.app.tmdb.modules.lists.request.AddMovieToListRequest;
import com.app.tmdb.modules.lists.response.MovieListItemResponse;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class AddMovieToListUseCase extends UseCase<AddMovieToListRequest, MovieListItemResponse> {

    private final MovieListRepository movieListRepository;
    private final MovieListItemRepository movieListItemRepository;

    @Override
    protected MovieListItemResponse doExecute(AddMovieToListRequest params) {
        var list = movieListRepository.findByIdAndUserEmail(params.getListId(), params.getUserEmail())
                .orElseThrow(() -> new BusinessException(MovieListError.LIST_NOT_FOUND));

        if (movieListItemRepository.existsByMovieListIdAndTmdbMovieId(params.getListId(), params.getTmdbMovieId())) {
            throw new BusinessException(MovieListError.MOVIE_ALREADY_IN_LIST);
        }

        MovieListItemEntity item = new MovieListItemEntity();
        item.setMovieList(list);
        item.setTmdbMovieId(params.getTmdbMovieId());
        item.setAddedAt(Instant.now());

        MovieListItemEntity saved = movieListItemRepository.save(item);
        return MovieListItemResponse.from(saved);
    }
}

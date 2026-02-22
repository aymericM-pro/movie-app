package com.app.tmdb.modules.lists.usecases;

import com.app.tmdb.modules.lists.entity.MovieListEntity;
import com.app.tmdb.modules.lists.repository.MovieListRepository;
import com.app.tmdb.modules.lists.request.CreateListRequest;
import com.app.tmdb.modules.lists.response.MovieListResponse;
import com.app.tmdb.usecase.UseCase;
import com.app.tmdb.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class CreateMovieListUseCase extends UseCase<CreateListRequest, MovieListResponse> {

    private final MovieListRepository movieListRepository;
    private final UserRepository userRepository;

    @Override
    protected MovieListResponse doExecute(CreateListRequest params) {
        var user = userRepository.findByEmail(params.getUserEmail())
                .orElseThrow(() -> new IllegalStateException("User not found: " + params.getUserEmail()));

        MovieListEntity entity = new MovieListEntity();
        entity.setUser(user);
        entity.setName(params.getName());
        entity.setCreatedAt(Instant.now());

        MovieListEntity saved = movieListRepository.save(entity);
        return MovieListResponse.fromWithoutItems(saved);
    }
}

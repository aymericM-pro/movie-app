package com.app.tmdb.modules.genders.usecases;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.modules.genders.requests.GetGenresRequest;
import com.app.tmdb.modules.genders.responses.GenderResponse;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetGenresUseCase
        extends UseCase<GetGenresRequest, List<GenderResponse>> {

    private final TmdbClient tmdbClient;

    @Override
    protected List<GenderResponse> doExecute(GetGenresRequest params) {
        return tmdbClient.getGenres(params.getLanguage())
                .stream()
                .map(GenderResponse::from)
                .toList();
    }
}

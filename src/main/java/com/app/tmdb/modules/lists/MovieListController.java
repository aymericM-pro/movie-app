package com.app.tmdb.modules.lists;

import com.app.tmdb.ApiResult;
import com.app.tmdb.modules.lists.request.AddMovieToListRequest;
import com.app.tmdb.modules.lists.request.CreateListRequest;
import com.app.tmdb.modules.lists.request.GetUserListsRequest;
import com.app.tmdb.modules.lists.request.ListIdRequest;
import com.app.tmdb.modules.lists.response.MovieListItemResponse;
import com.app.tmdb.modules.lists.response.MovieListResponse;
import com.app.tmdb.modules.lists.usecases.*;
import com.app.tmdb.usecase.UseCaseExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lists")
public class MovieListController {

    private final UseCaseExecutor executor;

    @GetMapping
    public ResponseEntity<ApiResult<List<MovieListResponse>>> getUserLists(
            @AuthenticationPrincipal UserDetails user
    ) {
        GetUserListsRequest request = new GetUserListsRequest();
        request.setUserEmail(user.getUsername());

        return ResponseEntity.ok(ApiResult.from(
                executor.execute(GetUserListsUseCase.class, request),
                HttpStatus.OK.value()
        ));
    }

    @PostMapping
    public ResponseEntity<ApiResult<MovieListResponse>> createList(
            @AuthenticationPrincipal UserDetails user,
            @RequestBody CreateListRequest request
    ) {
        request.setUserEmail(user.getUsername());

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResult.from(
                executor.execute(CreateMovieListUseCase.class, request),
                HttpStatus.CREATED.value()
        ));
    }

    @GetMapping("/{listId}")
    public ResponseEntity<ApiResult<MovieListResponse>> getList(
            @AuthenticationPrincipal UserDetails user,
            @PathVariable UUID listId
    ) {
        ListIdRequest request = new ListIdRequest();
        request.setUserEmail(user.getUsername());
        request.setListId(listId);

        return ResponseEntity.ok(ApiResult.from(
                executor.execute(GetMovieListUseCase.class, request),
                HttpStatus.OK.value()
        ));
    }

    @DeleteMapping("/{listId}")
    public ResponseEntity<Void> deleteList(
            @AuthenticationPrincipal UserDetails user,
            @PathVariable UUID listId
    ) {
        ListIdRequest request = new ListIdRequest();
        request.setUserEmail(user.getUsername());
        request.setListId(listId);

        executor.execute(DeleteMovieListUseCase.class, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{listId}/movies")
    public ResponseEntity<ApiResult<MovieListItemResponse>> addMovie(
            @AuthenticationPrincipal UserDetails user,
            @PathVariable UUID listId,
            @RequestBody AddMovieToListRequest request
    ) {
        request.setUserEmail(user.getUsername());
        request.setListId(listId);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResult.from(
                executor.execute(AddMovieToListUseCase.class, request),
                HttpStatus.CREATED.value()
        ));
    }

    @DeleteMapping("/{listId}/movies/{tmdbMovieId}")
    public ResponseEntity<Void> removeMovie(
            @AuthenticationPrincipal UserDetails user,
            @PathVariable UUID listId,
            @PathVariable Long tmdbMovieId
    ) {
        ListIdRequest request = new ListIdRequest();
        request.setUserEmail(user.getUsername());
        request.setListId(listId);
        request.setTmdbMovieId(tmdbMovieId);

        executor.execute(RemoveMovieFromListUseCase.class, request);
        return ResponseEntity.noContent().build();
    }
}

package com.app.tmdb.modules.lists.request;

import com.app.tmdb.models.request.ServiceParams;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddMovieToListRequest extends ServiceParams {

    private String userEmail;
    private UUID listId;
    private Long tmdbMovieId;

    @Override
    protected void validate() {
        checkString(userEmail, "userEmail", false, null, null);
        if (listId == null) fail("listId is required");
        checkLong(tmdbMovieId, "tmdbMovieId", false, true);
    }
}

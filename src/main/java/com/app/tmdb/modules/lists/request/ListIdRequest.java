package com.app.tmdb.modules.lists.request;

import com.app.tmdb.models.request.ServiceParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ListIdRequest extends ServiceParams {

    private String userEmail;
    private UUID listId;
    private Long tmdbMovieId;

    @Override
    protected void collectViolations(List<String> v) {
        checkString(v, userEmail, "userEmail", false, null, null);
        if (listId == null) v.add("listId is required");
    }
}

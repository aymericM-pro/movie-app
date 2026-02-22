package com.app.tmdb.modules.lists.request;

import com.app.tmdb.models.request.ServiceParams;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserListsRequest extends ServiceParams {

    private String userEmail;

    @Override
    protected void validate() {
        checkString(userEmail, "userEmail", false, null, null);
    }
}

package com.app.tmdb.modules.lists.request;

import com.app.tmdb.models.request.ServiceParams;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateListRequest extends ServiceParams {

    private String userEmail;
    private String name;

    @Override
    protected void validate() {
        checkString(userEmail, "userEmail", false, null, null);
        checkString(name, "name", false, 1, 255);
    }
}

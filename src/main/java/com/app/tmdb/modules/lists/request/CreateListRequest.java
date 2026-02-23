package com.app.tmdb.modules.lists.request;

import com.app.tmdb.models.request.ServiceParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateListRequest extends ServiceParams {

    private String userEmail;
    private String name;

    @Override
    protected void collectViolations(List<String> v) {
        checkString(v, userEmail, "userEmail", false, null, null);
        checkString(v, name, "name", false, 1, 255);
    }
}

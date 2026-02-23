package com.app.tmdb.models.request;

import com.app.tmdb.errors.ValidationException;

import java.util.ArrayList;
import java.util.List;

public abstract class ServiceParams {

    public final void validateParams() {
        List<String> violations = new ArrayList<>();
        collectViolations(violations);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
    }

    protected abstract void collectViolations(List<String> violations);

    protected void checkString(List<String> v, String value,
                               String field, boolean optional,
                               Integer min, Integer max) {
        if (value == null || value.isBlank()) {
            if (!optional) v.add(field + " is required");
            return;
        }
        if (min != null && value.length() < min)
            v.add(field + " length must be >= " + min);
        if (max != null && value.length() > max)
            v.add(field + " length must be <= " + max);
    }

    protected void checkInt(List<String> v, Integer value,
                            String field, boolean optional,
                            Integer min, Integer max) {
        if (value == null) {
            if (!optional) v.add(field + " is required");
            return;
        }
        if (min != null && value < min) v.add(field + " must be >= " + min);
        if (max != null && value > max) v.add(field + " must be <= " + max);
    }

    protected void checkLong(List<String> v, Long value,
                             String field, boolean optional,
                             boolean positiveOnly) {
        if (value == null) {
            if (!optional) v.add(field + " is required");
            return;
        }
        if (positiveOnly && value <= 0) v.add(field + " must be positive");
    }
}
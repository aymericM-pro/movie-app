package com.app.tmdb.models.request;

public abstract class ServiceParams {

    public final void validateParams() {
        validate();
    }

    protected abstract void validate();

    protected void checkInt(Integer value, String name, boolean optional, Integer min, Integer max) {
        if (value == null) {
            if (optional) return;
            fail(name + " is required");
        }
        if (min != null && value < min) fail(name + " must be >= " + min);
        if (max != null && value > max) fail(name + " must be <= " + max);
    }

    protected void checkLong(Long value, String name, boolean optional, boolean positiveOnly) {
        if (value == null) {
            if (optional) return;
            fail(name + " is required");
        }
        if (positiveOnly && value <= 0) fail(name + " must be positive");
    }

    protected void checkString(String value, String name, boolean optional, Integer min, Integer max) {
        if (value == null) {
            if (optional) return;
            fail(name + " is required");
        }
        if (value.isBlank()) fail(name + " must not be blank");
        if (min != null && value.length() < min) fail(name + " length must be >= " + min);
        if (max != null && value.length() > max) fail(name + " length must be <= " + max);
    }

    protected void fail(String message) {
        throw new IllegalArgumentException(message);
    }
}

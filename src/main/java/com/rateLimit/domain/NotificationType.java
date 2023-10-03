package com.rateLimit.domain;

import javax.annotation.Nullable;

public enum NotificationType {
    NEWS,
    MARKETING,
    STATUS;

    /**
     * @return the right enum or null if string is null or type does not match.
     */
    public static NotificationType of(@Nullable String type) {
        try {
            return NotificationType.valueOf(type);
        } catch (NullPointerException | IllegalArgumentException ignore) {}
        return null;
    }
}

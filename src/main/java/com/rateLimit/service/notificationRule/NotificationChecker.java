package com.rateLimit.service.notificationRule;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.rateLimit.domain.NotificationType;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Notification Checker, abstract class that checks if a recipient is available to be notify.
 */
public abstract class NotificationChecker {

    protected Integer maxNotifications;
    protected NotificationType type;
    protected Cache<String, Integer> loader;

    public NotificationChecker(Integer maxNotifications, NotificationType type, TimeUnit timeUnit) {
        this.maxNotifications = maxNotifications;
        this.type = type;
        this.loader = CacheBuilder.newBuilder()
                .expireAfterWrite(1, timeUnit)
                .build();
    }

    public Boolean checkNotification(String recipient) {
        Integer countNotifications = Optional.ofNullable(this.loader.getIfPresent(recipient))
                .map(it -> it - 1)
                .orElse(this.maxNotifications - 1);
        this.loader.put(recipient, countNotifications);
        return countNotifications >= 0;
    }

    @VisibleForTesting
    public void invalidateAll() {
        this.loader.invalidateAll();
    }
}

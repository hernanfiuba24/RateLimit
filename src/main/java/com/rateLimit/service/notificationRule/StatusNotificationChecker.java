package com.rateLimit.service.notificationRule;

import com.google.common.cache.Cache;
import com.rateLimit.domain.NotificationType;

/**
 * Status notification checks if a recipient {@param <String>} does not receive two notifications per minute.
 */
public class StatusNotificationChecker extends NotificationChecker {

    public StatusNotificationChecker(Cache<String, Integer> loader) {
        super(2, NotificationType.STATUS, loader);
    }

}

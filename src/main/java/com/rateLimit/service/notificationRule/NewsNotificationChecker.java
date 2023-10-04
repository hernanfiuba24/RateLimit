package com.rateLimit.service.notificationRule;

import com.google.common.cache.Cache;
import com.rateLimit.domain.NotificationType;

/**
 * New notification checks if a recipient {@param <String>} does not receive one notifications per day.
 */
public class NewsNotificationChecker extends NotificationChecker {

    public NewsNotificationChecker(Cache<String, Integer> loader) {
        super(1, NotificationType.NEWS, loader);
    }
}

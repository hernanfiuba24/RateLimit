package com.rateLimit.service.notificationRule;

import com.google.common.cache.Cache;
import com.rateLimit.domain.NotificationType;

/**
 * Marketing notification checks if a recipient {@param <String>} does not receive three notifications per hour.
 */
public class MarketingNotificationChecker extends NotificationChecker {

    public MarketingNotificationChecker(Cache<String, Integer> loader) {
        super(3, NotificationType.MARKETING, loader);
    }
}

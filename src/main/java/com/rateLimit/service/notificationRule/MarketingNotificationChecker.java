package com.rateLimit.service.notificationRule;

import com.rateLimit.domain.NotificationType;

import java.util.concurrent.TimeUnit;

/**
 * Marketing notification checks if a recipient {@param <String>} does not receive three notifications per hour.
 */
public class MarketingNotificationChecker extends NotificationChecker {

    public MarketingNotificationChecker() {
        super(3, NotificationType.MARKETING, TimeUnit.HOURS);
    }
}

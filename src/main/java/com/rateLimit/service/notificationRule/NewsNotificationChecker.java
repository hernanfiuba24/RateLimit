package com.rateLimit.service.notificationRule;

import com.rateLimit.domain.NotificationType;

import java.util.concurrent.TimeUnit;

/**
 * New notification checks if a recipient {@param <String>} does not receive one notifications per day.
 */
public class NewsNotificationChecker extends NotificationChecker {

    public NewsNotificationChecker() {
        super(1, NotificationType.NEWS, TimeUnit.DAYS);
    }
}

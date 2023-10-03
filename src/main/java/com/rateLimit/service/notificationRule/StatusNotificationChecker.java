package com.rateLimit.service.notificationRule;

import com.rateLimit.domain.NotificationType;

import java.util.concurrent.TimeUnit;

/**
 * Status notification checks if a recipient {@param <String>} does not receive two notifications per minute.
 */
public class StatusNotificationChecker extends NotificationChecker {

    public StatusNotificationChecker() {
        super(2, NotificationType.STATUS, TimeUnit.MINUTES);
    }

}

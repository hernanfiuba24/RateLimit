package com.rateLimit.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.rateLimit.domain.Notification;
import com.rateLimit.domain.NotificationType;
import com.rateLimit.domain.Status;
import com.rateLimit.service.notificationRule.MarketingNotificationChecker;
import com.rateLimit.service.notificationRule.NewsNotificationChecker;
import com.rateLimit.service.notificationRule.NotificationChecker;
import com.rateLimit.service.notificationRule.StatusNotificationChecker;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * NotificationSender process a {@link List} of {@link Notification}s, it sends notification if the recipient is available to be notify.
 */
public class NotificationSender {
    private HashMap<NotificationType, NotificationChecker> notificationCheckers;

    public NotificationSender() {
        this.notificationCheckers = new HashMap();
        this.notificationCheckers.put(NotificationType.STATUS, new StatusNotificationChecker(newCache(TimeUnit.MINUTES)));
        this.notificationCheckers.put(NotificationType.MARKETING, new MarketingNotificationChecker(newCache(TimeUnit.HOURS)));
        this.notificationCheckers.put(NotificationType.NEWS, new NewsNotificationChecker(newCache(TimeUnit.DAYS)));
    }

    public List<Notification> send(List<Notification> notifications) {
        return notifications.stream()
                .map(notification -> {
                    NotificationType notificationType = NotificationType.of(notification.getType());
                    if (notificationType != null && this.notificationCheckers.get(notificationType).checkNotification(notification.getRecipient())) {
                        send(notification);
                    } else {
                        notification.setStatus(Status.FAILED);
                    }
                    return notification;
                }).collect(Collectors.toList());
    }

    private Notification send(Notification notification) {
        // TODO: Send notification by the right channel
        notification.setStatus(Status.IN_PROGRESS);
        return notification;

    }

    private Cache<String, Integer> newCache(TimeUnit timeUnit) {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(1, timeUnit)
                .build();
    }
}

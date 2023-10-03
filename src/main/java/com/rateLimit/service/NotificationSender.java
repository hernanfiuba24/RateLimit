package com.rateLimit.service;

import com.rateLimit.domain.Notification;
import com.rateLimit.domain.NotificationType;
import com.rateLimit.domain.Status;
import com.rateLimit.service.notificationRule.MarketingNotificationChecker;
import com.rateLimit.service.notificationRule.NewsNotificationChecker;
import com.rateLimit.service.notificationRule.NotificationChecker;
import com.rateLimit.service.notificationRule.StatusNotificationChecker;

import java.util.HashMap;
import java.util.List;

/**
 * NotificationSender sends a {@link List} of {@link Notification}s if the recipient is available to be notify.
 */
public class NotificationSender {
    private HashMap<NotificationType, NotificationChecker> notificationCheckers;

    public NotificationSender() {
        this.notificationCheckers = new HashMap();
        this.notificationCheckers.put(NotificationType.STATUS, new StatusNotificationChecker());
        this.notificationCheckers.put(NotificationType.NEWS, new NewsNotificationChecker());
        this.notificationCheckers.put(NotificationType.MARKETING, new MarketingNotificationChecker());
    }

    public void send(List<Notification> notifications) {
        notifications.stream()
                .filter( notification -> {
                    NotificationType notificationType = NotificationType.of(notification.getType());
                    if (notificationType != null && this.notificationCheckers.get(notificationType).checkNotification(notification.getRecipient())) {
                        return true;
                    } else {
                        notification.setStatus(Status.FAILED);
                        return false;
                    }
                })
                .map(it -> send(it));
    }

    private Notification send(Notification notification) {
        // TODO: Send notification by the right channel
        notification.setStatus(Status.IN_PROGRESS);
        return notification;

    }
}

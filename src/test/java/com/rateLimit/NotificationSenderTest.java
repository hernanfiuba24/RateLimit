package com.rateLimit;

import com.google.common.collect.Lists;
import com.rateLimit.domain.Notification;
import com.rateLimit.domain.Status;
import com.rateLimit.service.NotificationSender;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class NotificationSenderTest {

    NotificationSender notificationSender = new NotificationSender();

    @Test
    public void testSendingANewsNotificationSuccessfully() {
        List<Notification> notifications = Lists.newArrayList(new Notification("Pepito", "Fulano", "NEWS", "Aloha"));
        notifications = notificationSender.send(notifications);
        Assert.assertEquals(Status.IN_PROGRESS, notifications.get(0).getStatus());
    }

    @Test
    public void testSendingThreeNewsNotificationShouldSuccessOnlyTheFirst() {
        List<Notification> notifications = Lists.newArrayList(
                new Notification("Pepito", "Fulano", "NEWS", "Aloha"),
                new Notification("Pepito", "Fulano", "NEWS", "Aloha"),
                new Notification("Pepito", "Fulano", "NEWS", "Aloha"));
        notifications = notificationSender.send(notifications);
        Assert.assertEquals(Status.IN_PROGRESS, notifications.get(0).getStatus());
        Assert.assertEquals(Status.FAILED, notifications.get(1).getStatus());
        Assert.assertEquals(Status.FAILED, notifications.get(2).getStatus());
    }
}

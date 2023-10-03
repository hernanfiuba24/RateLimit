package com.rateLimit;

import com.rateLimit.service.notificationRule.StatusNotificationChecker;
import org.junit.Assert;
import org.junit.Test;

public class StatusNotificationCheckerTest {

    private StatusNotificationChecker statusNotificationChecker = new StatusNotificationChecker();
    private static final String RECIPIENT = "Pepito";

    @Test
    public void testStatusNotificationCheckerSuccessfulOne() {
        Assert.assertTrue(statusNotificationChecker.checkNotification(RECIPIENT));
        statusNotificationChecker.invalidateAll();
    }

    @Test
    public void testStatusNotificationCheckerSuccessfulTwo() {
        Assert.assertTrue(statusNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertTrue(statusNotificationChecker.checkNotification(RECIPIENT));
        statusNotificationChecker.invalidateAll();
    }

    @Test
    public void testStatusNotificationCheckerFailTheThird() {
        Assert.assertTrue(statusNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertTrue(statusNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertFalse(statusNotificationChecker.checkNotification(RECIPIENT));
        statusNotificationChecker.invalidateAll();
    }

}

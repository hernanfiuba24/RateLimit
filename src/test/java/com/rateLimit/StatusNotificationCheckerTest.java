package com.rateLimit;

import com.google.common.cache.Cache;
import com.rateLimit.service.notificationRule.StatusNotificationChecker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class StatusNotificationCheckerTest {

    Cache<String, Integer> loaderMock;
    private StatusNotificationChecker statusNotificationChecker;
    private static final String RECIPIENT = "Pepito";

    @Before
    public void setup() {
        this.loaderMock = Mockito.mock(Cache.class);
        when(this.loaderMock.getIfPresent(RECIPIENT)).thenReturn(2, 1, 0);
        this.statusNotificationChecker = new StatusNotificationChecker(loaderMock);

    }

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
        when(this.loaderMock.getIfPresent(RECIPIENT)).thenReturn(2, 1, 0);
        Assert.assertTrue(statusNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertTrue(statusNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertFalse(statusNotificationChecker.checkNotification(RECIPIENT));
        when(this.loaderMock.getIfPresent(RECIPIENT)).thenReturn(2, 1, 0);
        Assert.assertTrue(statusNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertTrue(statusNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertFalse(statusNotificationChecker.checkNotification(RECIPIENT));
        statusNotificationChecker.invalidateAll();
    }

}

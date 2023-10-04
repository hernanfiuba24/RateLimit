package com.rateLimit.notificationRule;

import com.google.common.cache.Cache;
import com.rateLimit.service.notificationRule.MarketingNotificationChecker;
import com.rateLimit.service.notificationRule.NotificationChecker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class MarketingNotificationCheckerTest {

    Cache<String, Integer> loaderMock;
    private NotificationChecker marketingNotificationChecker;
    private static final String RECIPIENT = "Pepito";

    @Before
    public void setup() {
        this.loaderMock = Mockito.mock(Cache.class);
        when(this.loaderMock.getIfPresent(RECIPIENT)).thenReturn(3, 2, 1, 0);
        this.marketingNotificationChecker = new MarketingNotificationChecker(loaderMock);

    }

    @Test
    public void testMarketingNotificationCheckerSuccessfulOne() {
        Assert.assertTrue(marketingNotificationChecker.checkNotification(RECIPIENT));
        marketingNotificationChecker.invalidateAll();
    }

    @Test
    public void testMarketingNotificationCheckerSuccessfulThree() {
        Assert.assertTrue(marketingNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertTrue(marketingNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertTrue(marketingNotificationChecker.checkNotification(RECIPIENT));
        marketingNotificationChecker.invalidateAll();
    }

    @Test
    public void testMarketingNotificationCheckerFailTheFour() {
        when(this.loaderMock.getIfPresent(RECIPIENT)).thenReturn(3, 2, 1, 0);
        Assert.assertTrue(marketingNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertTrue(marketingNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertTrue(marketingNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertFalse(marketingNotificationChecker.checkNotification(RECIPIENT));
        when(this.loaderMock.getIfPresent(RECIPIENT)).thenReturn(3, 2, 1, 0);
        Assert.assertTrue(marketingNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertTrue(marketingNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertTrue(marketingNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertFalse(marketingNotificationChecker.checkNotification(RECIPIENT));
        marketingNotificationChecker.invalidateAll();
    }

}

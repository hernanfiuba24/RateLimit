package com.rateLimit.notificationRule;

import com.google.common.cache.Cache;
import com.rateLimit.service.notificationRule.NewsNotificationChecker;
import com.rateLimit.service.notificationRule.NotificationChecker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NewsNotificationCheckerTest {

    Cache<String, Integer> loaderMock;
    private NotificationChecker newsNotificationChecker;
    private static final String RECIPIENT = "Pepito";

    @Before
    public void setup() {
        this.loaderMock = Mockito.mock(Cache.class);
        when(this.loaderMock.getIfPresent(RECIPIENT)).thenReturn(1, 0);
        this.newsNotificationChecker = new NewsNotificationChecker(loaderMock);

    }

    @Test
    public void testNewsNotificationCheckerSuccessfulOne() {
        Assert.assertTrue(newsNotificationChecker.checkNotification(RECIPIENT));
        newsNotificationChecker.invalidateAll();
    }

    @Test
    public void testNewsNotificationCheckerFailTheSecond() {
        when(this.loaderMock.getIfPresent(RECIPIENT)).thenReturn(1, 0);
        Assert.assertTrue(newsNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertFalse(newsNotificationChecker.checkNotification(RECIPIENT));
        when(this.loaderMock.getIfPresent(RECIPIENT)).thenReturn(1, 0);
        Assert.assertTrue(newsNotificationChecker.checkNotification(RECIPIENT));
        Assert.assertFalse(newsNotificationChecker.checkNotification(RECIPIENT));
        newsNotificationChecker.invalidateAll();
    }

}

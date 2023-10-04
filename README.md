# Notification Service
## Description
Notification Service protect recipients from getting too many notifications.
It means the system rejects request that are over the limit

## Rules
- Status Notification: Not more than 2 per minute for each recipient
- Marketing Notification: Not more than 3 per hour for each recipient
- News Notification: Not more than 1 per day for each recipient

## Test
Integration test: NotificationSenderTest, NewsNotificationCheckerTest, ...

## Other considerations

It is a maven project
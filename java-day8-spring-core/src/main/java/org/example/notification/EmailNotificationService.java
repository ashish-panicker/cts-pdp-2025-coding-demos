package org.example.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("emailNotificationService")
public class EmailNotificationService implements NotificationService {

    @Value("${email.enabled}")
    private boolean isEnabled;

    @Override
    public void notifyCustomer(String to, String message) {
        if(!isEnabled) {
            System.err.println("[EmailNotificationService]: Email sending is disabled.");
            return;
        }
        System.out.println("[EmailNotificationService] To: " + to + ", Message: " + message);
    }
}

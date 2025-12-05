package org.example.factory;

import org.example.service.EmailService;
import org.example.service.MessageService;
import org.example.service.TextService;

public class ServiceFactory {
    public static MessageService getMessagingService(String type) {
        return switch (type) {
            case "sms" -> new TextService();
            case "email" -> new EmailService();
            default -> throw new IllegalArgumentException("Service type requested not supported.");
        };
    }
}

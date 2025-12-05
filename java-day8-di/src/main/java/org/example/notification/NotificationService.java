package org.example.notification;

import org.example.service.MessageService;

public class NotificationService {

    private final MessageService service;

    public NotificationService(MessageService service) {
        this.service = service;
    }

    public void notifyClient(String from,String to, String message) {
        service.sendMessage(from, to, message);

    }
}

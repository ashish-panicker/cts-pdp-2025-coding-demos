package org.example.container;

import org.example.notification.NotificationService;
import org.example.service.EmailService;
import org.example.service.MessageService;

import java.util.HashMap;
import java.util.Map;

public class IocContainer {

    private final Map<Class<?>, Object> registry = new HashMap<>();

    public IocContainer() {
        registry.put(MessageService.class, new EmailService());
        registry.put(NotificationService.class, new NotificationService(get(MessageService.class)));
    }

    public  <T> T get(Class<T> cls) {
        return (T) registry.get(cls);

    }
}

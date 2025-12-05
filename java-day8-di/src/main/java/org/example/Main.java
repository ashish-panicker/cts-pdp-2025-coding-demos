package org.example;

import org.example.container.IocContainer;
import org.example.notification.NotificationService;

/* Dependency Injection
 * A design technique where an object does not create it's own dependencies.
 * Instead it is requesting these dependencies from an external component or framework.
 *
 * Inversion of Control
 * A broad design principle where you give the control of object creation and lifecycle
 * to a container or an external framework.
 */
public class Main {
    public static void main(String[] args) {
        var container = new IocContainer();
        var notificationService2 = container.get(NotificationService.class);
        notificationService2.notifyClient("Ashish", "Rahul", "Thank you");
    }
}

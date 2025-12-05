package org.example;

import org.example.config.AppConfig;
import org.example.model.Customer;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * BeanFactory
 * - Creating & Managing Beans
 * - DI
 *
 * ApplicationContext
 * - Creating beans
 * - Managing bean life cycle
 * - Injecting dependencies
 * - Event publishing
 * - AOP proxy creation
 * - Resource Loading
 * - Internationalization
 *
 * BeanFactory              -> XmlBeanFactory, ApplicationContext
 * ApplicationContext       -> ClassPathXmlApplicationContext FileSystemXmlApplicationContext AnnotationConfigApplicationContext
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Customer customer = new Customer("C001", "ashish", "ashish@gmail.com");

        Order order = new Order("O001", customer);
        order.addItem(new OrderItem("Gaming Mouse", 1, 1500));
        order.addItem(new OrderItem("Mechanical Keyboard", 1, 3500));
        order.addItem(new OrderItem("Headset", 1, 4500));

        OrderService orderService = context.getBean(OrderService.class);

        orderService.processOrder(order);

        System.out.println("[Order processed]: Order Id: " + order.getId() +
                ", Total Paid: " + order.getTotalAmount() +
                ", Payment Status: " + (order.isPaymentProcessed() ? "Yes" : "No"));

        context.close();
    }
}

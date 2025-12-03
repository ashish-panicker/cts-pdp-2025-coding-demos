# Design patterns & SOLID Principles

**SOLID**

- Single Responsibility
- Open Closed
- Liskov Substitution
- Interface Segregation
- Dependency Injection

Reusable, well tested, time proven solutions to recurring design problems that improves overall maintainability flexibility and structure of code. 

- Creational
- Structural
- Behavioural

**Problem statement**

A trading platform that requires a central risk management stratergy. This has to hold exchange wide 
limits across all orders. This must be shared across all clients who are trading using this platform. 
This will be lazily initialized as the rules are going to be loaded from the database.

We will use `Singleton Design Pattern` here.


```java
class OrderProcessor {
    void processOrder(Order order) {
        RulesEngine engine = new RulesEngine(); // Not recomended
        engine.validate(order);
    }
}
```
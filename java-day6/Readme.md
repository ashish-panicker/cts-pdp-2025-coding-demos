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
        RulesEngine engine = new RulesEngine(); // Not recommended
        engine.validate(order);
    }
}
```

What does an interface represent?
- Capability or Behaviour
- A drone can `fly`, `track`, `record`

What does an abstract class represent?
- What the object is or the structure of an object
- Use this when classes needs to share behaviour, state and reusable code

```java
// Ride interface represents some common behaviour that is present for every Ride option
interface Ride {
    double calculateFare(double distance);
    String description();
    default void trackLiveLocation() {}
}

// All ride has some common features
// Base fare, price per km, surge pricing calculation
abstract class BaseRide implements Ride {
    protected double pricePerKm;
    protected double baseFare;
    protected double surgeFactor;
}
```
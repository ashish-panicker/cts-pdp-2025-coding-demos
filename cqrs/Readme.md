# CQS vs CQRS — Start from First Principles

## **Command–Query Separation (CQS)**

>A method should either change state (Command) or return data (Query) — but never both.

Example
```java
void createOrder(CreateOrderCommand cmd); // command
OrderView getOrder(String orderId);       // query
```

Benefits
- Clear intent
- Easier testing
-Predictable side effects

CQS does NOT mandate separate services or databases

## **CQRS — Command Query Responsibility Segregation**

CQRS extends CQS to architecture level.

**Core Idea**
>Separate models, services, and data stores for writes and reads

```text
[ Command Model ]   →   Events   →   [ Query Model ]
     (Write)                           (Read)
```
What is separated?

| Aspect         | Command Side               | Query Side            |
| -------------- | -------------------------- | --------------------- |
| Responsibility | Business rules, validation | Fast reads            |
| API            | POST / PUT / DELETE        | GET                   |
| DB             | Normalized                 | Denormalized          |
| Consistency    | Strong                     | Eventually consistent |
| Tech           | JPA, transactions          | Read DB, cache        |


## **When to Use CQRS**

Use CQRS when at least 2–3 of these are true:
- High read/write imbalance
- Complex business validations
- Need event-driven integrations
- Multiple read projections
- Audit / replay requirements
- Scalability of reads matters
- Async processing acceptable

Real Use Cases
- Order Management
- Payment Processing
- Inventory & Reservation
- Banking / Ledger systems
- Analytics-heavy platforms

## What problem does CQRS solve?

### Problem

How to implement a query that retrieves data from multiple services in a microservice architecture?

### Solution

Define a view database, which is a read-only `replica` that is designed specifically to support that query, 
or a group related queries. The application keeps the database up to date by subscribing to Domain events published 
by the service that own the data. The type of database and its schema are optimized for the query or queries. 
It’s often a NoSQL database, such as a document database or a key-value store.

## References
- https://microservices.io/patterns/data/cqrs.html
- https://learn.microsoft.com/en-us/azure/architecture/patterns/cqrs
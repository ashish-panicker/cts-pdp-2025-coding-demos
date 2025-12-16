# Kafka

Kafka is a distributed event streaming system. Can also consider it as a durable, distributed event log.
kafka is not just an message queue.

## Need for kafka

Traditional Systems
- Producer to consumer tight coupling
- Scaling consumers independently
- Replaying past events not possible
- Handling traffic surges

Kafka solves these by
- Decoupling producer and consumer
- Persisting the events in a durable manner
- Allowing multiple consumers to read the same event
- Supports event replay
- Scaling horizontally

## Key blocks

### Event (Record/Message)

An event is an action that has happened.
```text
Key:123
Value: OrderPlaced [OrderId, UserId, Amount]
TimeStamp: 16-12-2025 15:06:00
```

Kafka is append only, you cannot edit an event, events are `immutable`.

### Topic

Topic is where logs get aggregated. You can think of them as data stores where events get collected.
Topic is like a table where only insert is allowed, no delete or edit are permitted.

### Partition

Each topic is split into partitions. They are ordered, immutable logs that split the topic into smaller parts.
Partitions are essential for:
- Scalability
- Parallelism 
- Ordered storage
- Key based data assignment
- Durability

After a topic is created, we define the number of partitions that we need for that topic.
Kafka will route messages into these partitions:
- with key, hash the key, the use modulo(key_hash `%` num_partitions) to find the partition number
- without key, kafka will distribute messages in round robin format

### Offset

Offset is a unique sequential number assigned to the messages in a partition. These numbers are unique within a 
partition. 

### Producer

### Consumer

## References
- https://stackoverflow.com/questions/38024514/understanding-kafka-topics-and-partitions
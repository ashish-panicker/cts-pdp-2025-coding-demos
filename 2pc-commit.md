# Two Phase Commit

It is a distributed transaction protocl and is used to ensure `Atomicity` across multiple databases and services.
`Atomicity` means either all services commit or no services commit.

## Why is it used?

Take the case of a distributed system
- Order service -> Order DB
- Payment Service -> Payment DB
- Inventory Service -> Inventory DB
If one service commits the data but the other service fails to commit, this will result it corrupted data.

2PC commit cordinates commits across all services.

## Key Roles

### Cordinator
- Orchastrates the transaction
- Makes the final commit call

### Participants
- Databases/services
- Order DB

### Two Phases

#### Phase 1: Prepare/Voting Phase

Important to know that no data is committed in this step. Everythig is in a ready to commit state.

Steps
- Cordinator sends the Prepare request to all Participants
- Each Participant
  - Execute the change locally
  - Write changes into undo/redo logs
  - Locks the resources
  - Responds with Yes/No

#### Phase 2: Decision Phase

Steps
- Case A: Participants return Yes
  - Cordinator sends COMMIT
  - Release Locks
  - Acknowledge completion
  
- Case B: Participants return No
  - Cordinator sends ROLLBACK
  - Particpants will undo the changes that have been made lcoally
  - Release Locks


### What does this Guarentte?

2PC commit guarentees `Atomicity` but fails at `high availability` and `non-blocking` operations

### Major Issues

**Blocking problem**
If the co-ordinator crashes, 
- Participants will hold the lock and wait indefinitely
- Cannot decide to commit or rollback

**Scalability**
- Long held Locks
- Multiple network calls
- Slow turnaround

**Single point of failure**
- Cordinator is most critical
- Recovery is complex


Sample
```java
// Needs XA Aware Resource Manager
// Require JTA Transaction Manager
// Heavy runtime cost
// Spring avoids XA/2PC
@Transactional
public void placeOrder() {
    orderRepo.save(order)
    paymentRepo.save(payment)
}
```

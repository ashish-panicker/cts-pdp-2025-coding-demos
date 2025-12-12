# Microservices & MSA
 
[Microservices.io](https://microservices.io/)

Microservice Architecture is an architecture style that structures an application as collection of 
two or more services that are 
- deployed independently
- loosely coupled

A service is a small autonomous, deployable unit that encapsulates a business capability. A service is a mini application
that ahs it's own
- codebase
- database
- deployment lifecycle
- ci/cd pipelines
- monitoring

## Evolution to Microservice applications

### Single tier Applications

Time frame: 1970-1990, All 3 tiers, ui, business logic, data layer are all managed by that single application. 

### Two tier or Applications - Client Server

UI tier [Client] / Data + Business Logic tier [Server]

### Three tier Applications

UI/ Business Logic/ Data Layer all are separate
- these applications were deployed as a monolith example: .war, .ear

### n tier Applications

### SOA


Late 2000 to early 2010 companies like Amazon and Netflix started to decompose their large codebase into 
small individual services which allowed them to deploy and manage them independently. This allowed them to scale 
applications individually. This allowed smaller teams to own these services plus their infra. This along with rise of
containers, devops, they all popularized microservices as the answer to scaling and velocity needs. 

## Advantages

- Independent deployment ans ownership
- Scaling
- Fault isolation
- Different tech stack for diff domains

[Microservice vs Monolith](https://www.atlassian.com/microservices/microservices-architecture/microservices-vs-monolith?utm_source=chatgpt.com)
[Microservice vs Monolith](https://aws.amazon.com/compare/the-difference-between-monolithic-and-microservices-architecture/)

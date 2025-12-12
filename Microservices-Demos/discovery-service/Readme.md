# Service Discovery

Service discovery enables one service to find the instances of other services.

**Core Components**

- `Service Registry`: A database or directory where services register themselves
- `Service Instance`: Each service registers itself when starting up and then send heartbeats to the server.
- `Discovery Client`: Can happen via server side or client side discovery.
- `Load Balancing`

**Server side service discovery**

The client makes the request to the router/load balancer, which in turn queries the service registry and
then places the request.

**Client side service discovery**

The client queries the service registry, obtains the url of the required service, and then places the request.

**Advantages**

- Support for elastic scaling
- Logical name and physical addresses are decoupled

**Disadvantages**

- Adds one more service and it's complications

**Set up Eureka server**

- Use `@EnableEurekaServer` annotation on the discovery server application.
- Add the `spring-cloud-starter-netflix-eureka-client` in the client service.
- Set `eureka.client.service-url.defaultZone=http://localhost:8761`
- Set `eureka.instance.prefer-ip-address=true`
- 
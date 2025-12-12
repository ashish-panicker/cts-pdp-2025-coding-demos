# Order Service


## Feign Client

Spring Cloud Open Feign is a declarative HTTP client that integrates with spring. We define an interface
with `@FeignClient` annotation and define the service methods using `@PostMapping`. Spring will dynamically 
generate the implementation of this Feign client.

Features
- Interface to Dynamic Proxy: Spring will scan for all @FeignClient and generate dynamic proxies at startup.
- Encode/Decode: Automatic data encoding and decoding.
- Contract & Metadata: Spring mvc annotations will be scanned and used to understand the service methods.
- Load balancing and Discovery: Integrates with Eureka server, and spring cloud load balancer.
- Resilience: Supports circuit breaker, bulkhead pattern, retries.
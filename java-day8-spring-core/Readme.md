# Spring - Core framework concepts and explanations

**What is Spring Core**

Spring core is the core foundation of spring framework. It consists of:
- Ioc Container
- Dependency Injection
- Bean Lifecycle Management
- Configuration models (Java/XML based)
- Core utilities (`Environment`)

**Ioc Container** - responsible for managing the beans/dependencies and their lifecycle.

**Bean** - java objects managed by Ioc container. You can declare beans via xml or annotations.

**ApplicationContext** - Advanced Ioc container implementation. Responsible for object creation, management, injection

**Types of DI**

- Constructor based (recommended)
- Setter based

**Bean Scope** - defines how many instances of the bean must be made available based on type of application.
- Singleton: default scope, one instance per container
- prototype: new instance created every time a bean is requested
- request(web: one bean instance for every http request
- session(web: one bean instance for every session
- application(web: one bean instance for every application
- websocket(web):

**Bean lifecycle**
1. Instantiate bean
2. Inject dependencies
3. Call lifecycle callback methods
   - @PostContruct
   - init-method attribute in xml
4. Bean is ready
5. On app context shutdown
    - @PreDestroy
    - destroy-method

**Important annotations**
- `@Bean`
- `@Component`
- `@Repository`
- `@Service`
- `@Autorwired`
- `@Controller`
- `@RestController`
- `@Qualifier`
- `@Primary`
- `@Profile`
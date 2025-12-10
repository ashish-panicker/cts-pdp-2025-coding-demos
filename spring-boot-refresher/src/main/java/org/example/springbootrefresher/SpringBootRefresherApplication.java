package org.example.springbootrefresher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Spring boot autoconfiguration
 * Spring boot will automatically scan the class path and load all the spring beans
 * Spring achieves this the use of @Conditional annotation
 * Example:
 * spring-boot-starter-web is present
 * - automatically configure [DispatcherServlet, Embedded Tomcat]
 * spring-boot-starter-data-jpa is present
 * - automatically configure [Hibernate, DataSource, EntityManagerFactory, TransactionManager]
 * @SpringBootApplication is a shorthand for
 * - @SpringBootConfiguration - specialized @Configuration
 * - @EnableAutoConfiguration - trigger spring boot auto config
 * - @ComponentScan - scans the root package for beans
 *
 * How does autoconfiguration works?
 * Withe help of certain classes
 * - DataSourceAutoConfiguration
 * - WebMvcAutoConfiguration
 * - JpaRepositoriesAutoConfiguration
 *
 * How does a spring boot application start?
 *
 * 1. Create a SpringApplication instance
 *
 * 2. Prepare the environment
 * --- Load the properties/yaml files
 * --- Load the profiles
 * --- Load the Environment values
 * --- Command line args
 * --- Load ConfigData [from v2.4]
 *
 * 3. Create ApplicationContext
 * --- AnnotationConfigApplicationContext [Non web apps]
 * --- AnnotationConfigServletWebApplicationContext [Web MVC]
 * --- AnnotationConfigReactiveWebApplicationContext [Web Flux]
 *
 * 4. Apply ApplicationContext initializer
 *
 * 5. Perform Component Scan for
 * --- [@Service, @Repository, @Component, @Configuration, @RestController, @Controller]
 * --- load them into application context
 *
 * 6. Process autoconfiguration classes
 * --- data source setup
 * --- jackson setup
 * --- embedded tomcat setup
 * --- logging setup
 * --- bootstrap jpa modules
 *
 * 7. Create and initialize beans
 * --- execute constructor injection
 * --- @PostConstruct call backs
 * --- Bean post-processing
 * --- AOP proxies
 *
 * 8. Start the tomcat embedded server
 *
 * 9. Publish ApplicationReadyEvent
 */
@SpringBootApplication
public class SpringBootRefresherApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRefresherApplication.class, args);
    }

}

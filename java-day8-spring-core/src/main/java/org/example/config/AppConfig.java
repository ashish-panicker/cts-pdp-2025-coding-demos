package org.example.config;

import org.example.service.config.CardPaymentProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


// @Configuration - let's spring know that this is the configuration class
// @ComponentScan - list out the names of the packages that contains spring beans
//                  that needs to loaded automatically
// @PropertySource - loads external .properties files into the spring environment
//                   you can use @Value or Environment class to read props

@Configuration
@ComponentScan(basePackages = {"org.example"})
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties({
        CardPaymentProps.class
})
public class AppConfig {
}

package org.example.springbootrefresher.config;

import org.example.springbootrefresher.service.DBHeartBeatService;
import org.example.springbootrefresher.service.EmailReaderService;
import org.example.springbootrefresher.service.EmailSenderService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// Provide additional application configuration
// Marks the class as source of custom or additional configurations
@Configuration
@EnableConfigurationProperties({AppProps.class})
public class CustomConfiguration {


    @Bean
    @ConditionalOnBean(EmailReaderService.class)
    public EmailSenderService emailSenderService() {
        return new EmailSenderService();
    }

    @Bean
    @ConditionalOnProperty(
            name = "features.email.enabled",
            havingValue = "true", // features.email.enabled=true, then only bean will be created
            matchIfMissing = false // Do not create the bean if this property is missing
    )
    public EmailReaderService emailReaderService() {
        return new EmailReaderService();
    }

    //    @Bean
    @ConditionalOnClass(DataSource.class)
    public DBHeartBeatService dbHeartBeatService() {
        return new DBHeartBeatService();
    }
}

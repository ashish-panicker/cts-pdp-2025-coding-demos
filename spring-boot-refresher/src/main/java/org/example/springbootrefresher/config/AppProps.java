package org.example.springbootrefresher.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

// Supported by IDE
// Cleaner code
// Validated and type safe
@ConfigurationProperties(prefix = "app.datasource")
public record AppProps(int poolSize, int timeout, String key) {
}

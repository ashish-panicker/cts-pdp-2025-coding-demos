package org.example.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * ConfigurationProperties
 * - Avoid using @Value repeatedly
 * - Validate configuration
 * - Use default values
 * Not available in spring-core, needs to include spring-boot and spring-boot-autoconfigure deps
 */
@ConfigurationProperties(prefix = "payment.card")
public class CardPaymentProps {

    private String gatewayUrl;
    private int timeout;
    private int retries;

    public CardPaymentProps() {
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    @Override
    public String toString() {
        return "CardPaymentProps{" +
                "gatewayUrl='" + gatewayUrl + '\'' +
                ", timeout=" + timeout +
                ", retries=" + retries +
                '}';
    }
}

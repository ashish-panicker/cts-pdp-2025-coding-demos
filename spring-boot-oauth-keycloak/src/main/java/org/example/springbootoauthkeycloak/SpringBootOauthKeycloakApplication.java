package org.example.springbootoauthkeycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootOauthKeycloakApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOauthKeycloakApplication.class, args);
    }

    /*
     * HTTP call to get token from Keycloak
     *
     * curl --location 'http://localhost:8080/realms/demo/protocol/openid-connect/token' \
     * --header 'Content-Type: application/x-www-form-urlencoded' \
     * --data-urlencode 'grant_type=password' \
     * --data-urlencode 'client_id=spring-resource' \
     * --data-urlencode 'client_secret=spring-secret' \
     * --data-urlencode 'username=' \
     * --data-urlencode 'password=' \
     * --data-urlencode 'scope=openid'
     */

}

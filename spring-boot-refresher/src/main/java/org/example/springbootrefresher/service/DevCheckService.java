package org.example.springbootrefresher.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevCheckService {

    public DevCheckService() {
        System.err.println("DevCheckService");
    }
}

package com.backend.spring_security_oauth2.controller;


import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final ClientRegistrationRepository clientRegistrationRepository;

    public TestController(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @GetMapping("/test-oauth")
    public String testOAuth() {

        ClientRegistration google =
                clientRegistrationRepository.findByRegistrationId("google");

        if (google == null) {
            return "Google registration NOT found";
        }

        return "Google registration FOUND: "
                + google.getRegistrationId()
                + " | "
                + google.getClientName();
    }
}

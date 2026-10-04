package com.backend.spring_security_oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class demo {
    @GetMapping("/msg")
    public String message() {
        return "Hello Prudhvi";
    }
}

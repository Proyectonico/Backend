package com.javaproject.backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class HelloClass {
    @GetMapping("/hello")
    public String getMethodName() {
        return new String("Hello World");
    }
    
    
    
}


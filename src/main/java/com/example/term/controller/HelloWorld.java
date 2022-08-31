package com.example.term.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {


    @GetMapping("/ping")
    public String Ping() {
        return "ping";
    }
}

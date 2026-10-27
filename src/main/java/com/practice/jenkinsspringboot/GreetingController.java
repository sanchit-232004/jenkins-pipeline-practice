
package com.practice.jenkinsspringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/")
    public String home() {
        return "Spring Boot project is running!";
    }

    @GetMapping("/api/greet")
    public String greet(
            @RequestParam(defaultValue = "Developer") String name) {
        return "Hello, " + name + "! CI/CD pipeline is now implemented";
    }
}

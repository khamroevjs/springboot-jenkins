package com.example.springbootjenkins

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    @GetMapping
    fun index(): String {
        return "Hello World!"
    }

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello"
    }
}
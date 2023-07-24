package com.sorsix.backend.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/test")
class TestController {

    @GetMapping
    fun testAuth(principal: Principal): ResponseEntity<String>{
        return ResponseEntity.ok(principal.name);
    }
}
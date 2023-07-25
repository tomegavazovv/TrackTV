package com.sorsix.backend.api

import com.sorsix.backend.service.FavoriteCastService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/test")
class TestController(val favoriteCastService: FavoriteCastService) {

    @GetMapping
    fun testAuth(principal: Principal): ResponseEntity<String>{
        principal.name.toLong()
        return ResponseEntity.ok("");

    }


}
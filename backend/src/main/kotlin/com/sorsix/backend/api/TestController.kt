package com.sorsix.backend.api

import com.sorsix.backend.authentication.CustomPrincipal
import com.sorsix.backend.service.FavoriteCastService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/test")
class TestController(val favoriteCastService: FavoriteCastService) {

    @GetMapping
    fun testAuth(@AuthenticationPrincipal customPrincipal: CustomPrincipal): ResponseEntity<String>{
        return ResponseEntity.ok("${customPrincipal.userId}");
    }



}
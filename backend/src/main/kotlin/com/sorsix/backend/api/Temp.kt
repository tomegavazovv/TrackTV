package com.sorsix.backend.api

import com.sorsix.backend.repository.user.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


// privremeno za testiranje
@RestController
@RequestMapping("/api/")
class Temp(val repo:WatchShowRepository) {
    @GetMapping("/findAll")
    fun getFriendRequests() = repo.findAll()
}
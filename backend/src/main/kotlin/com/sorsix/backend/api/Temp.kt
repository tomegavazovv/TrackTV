package com.sorsix.backend.api

import com.sorsix.backend.repository.friendship.FriendRepository
import com.sorsix.backend.repository.friendship.FriendRequestRepository
import com.sorsix.backend.repository.movie.MovieCastRepository
import com.sorsix.backend.repository.movie.MovieRepository
import com.sorsix.backend.repository.show.EpisodeRepository
import com.sorsix.backend.repository.show.SeasonRepository
import com.sorsix.backend.repository.show.ShowCastRepository
import com.sorsix.backend.repository.show.ShowRepository
import com.sorsix.backend.repository.user.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


// privremeno za testiranje
@RestController
@RequestMapping("/api/")
class Temp(val repo:UserWatchShowRepository) {
    @GetMapping("/findAll")
    fun getFriendRequests() = repo.findAll()
}
package com.example.springboot3redis.user

import java.util.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userRepository: UserRepository,
) {

    @PostMapping("/users")
    fun create(@RequestBody user: User) {
        userRepository.save(user)
    }

    @GetMapping("/users/{id}")
    fun find(@PathVariable id: String): User? {
        return userRepository.findByIdOrNull(id)
    }

    @PutMapping("/users/{id}")
    fun update(@RequestBody user: User) {
        userRepository.save(user)
    }

    @DeleteMapping("/users/{id}")
    fun delete(@PathVariable id: String) {
        userRepository.deleteById(id)
    }
}

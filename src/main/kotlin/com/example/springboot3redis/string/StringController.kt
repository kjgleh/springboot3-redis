package com.example.springboot3redis.string

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StringController(
    private val stringRedisTemplate: StringRedisTemplate,
) {

    @PostMapping("/string")
    fun create(
        @RequestBody stringRequest: StringRequest,
    ) {
        return stringRedisTemplate.opsForValue().set(
            stringRequest.id.toString(),
            stringRequest.name,
        )
    }

    @GetMapping("/string/{id}")
    fun get(
        @PathVariable id: Long,
    ): String? {
        return stringRedisTemplate.opsForValue().get(id.toString())
    }

    @PutMapping("/string/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody stringRequest: StringRequest,
    ) {
        stringRedisTemplate.opsForValue().set(id.toString(), stringRequest.name)
    }

    @DeleteMapping("/string/{id}")
    fun delete(
        @PathVariable id: Long,
    ) {
        stringRedisTemplate.delete(id.toString())
    }

    data class StringRequest(
        val id: Long,
        val name: String,
    )
}

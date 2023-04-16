package com.example.springboot3redis.string

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StringSchemaController(
    private val stringRedisTemplate: StringRedisTemplate,
) {

    private val schema = "account"

    @PostMapping("/string-schema")
    fun create(
        @RequestBody stringRequest: StringRequest,
    ) {
        return stringRedisTemplate.opsForValue().set(
            "$schema:${stringRequest.id}",
            stringRequest.name,
        )
    }

    @GetMapping("/string-schema/{id}")
    fun get(
        @PathVariable id: Long,
    ): String? {
        return stringRedisTemplate.opsForValue().get("$schema:$id")
    }

    @GetMapping("/string-schema")
    fun findAll(): List<String>? {
        val keyPattern = "account:*"
        val keys = stringRedisTemplate.keys(keyPattern)
        if (keys.isEmpty()) {
            return emptyList()
        }
        return stringRedisTemplate.opsForValue().multiGet(keys)
    }

    @DeleteMapping("/string-schema")
    fun deleteAll(): Long {
        val keyPattern = "account:*"
        val keys = stringRedisTemplate.keys(keyPattern)
        if (keys.isEmpty()) {
            return 0L
        }
        return stringRedisTemplate.delete(keys)
    }

    data class StringRequest(
        val id: Long,
        val name: String,
    )
}

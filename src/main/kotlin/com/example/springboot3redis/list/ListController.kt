package com.example.springboot3redis.list

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ListController(
    private val redisTemplate: RedisTemplate<String, String>,
) {

    private val listName = "myLists"

    @PostMapping("/list")
    fun addToList(@RequestBody value: String): Long? {
        return redisTemplate.opsForList().rightPush(listName, value)
    }

    @GetMapping("/list")
    fun getList(@RequestParam start: Long, @RequestParam end: Long): List<String>? {
        return redisTemplate.opsForList().range(listName, start, end)
    }

    @DeleteMapping("/list/{value}")
    fun deleteListValue(@PathVariable value: String): Long? {
        return redisTemplate.opsForList().remove(listName, 1, value)
    }
}

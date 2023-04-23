package com.example.springboot3redis.lock

import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LockTestController(
    private val redisConnectionFactory: RedisConnectionFactory,
) {

    private val redisLocker = RedisLocker(
        redisConnectionFactory = redisConnectionFactory,
        registryKey = "lock:",
        expireAfter = 10000,
        timeout = 10,
    )

    @GetMapping("/lock")
    fun lock() {
        redisLocker.run("lockKey") {
            println("running")
        }
    }
}

package com.example.springboot3redis.user

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash
class User(
    @Id
    val id: String,
    val name: String,
)

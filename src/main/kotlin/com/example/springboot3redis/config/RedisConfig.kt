package com.example.springboot3redis.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class RedisConfig {

    @Bean
    fun redisConnectionFactory(
        @Value("\${spring.data.redis.host}")
        host: String,
        @Value("\${spring.data.redis.port}")
        port: Int,
    ): RedisConnectionFactory {
        return LettuceConnectionFactory(host, port)
    }

    @Bean
    fun hashRedisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<*, *> {
        return RedisTemplate<String, Any>().apply {
            this.setConnectionFactory(redisConnectionFactory)
        }
    }
}

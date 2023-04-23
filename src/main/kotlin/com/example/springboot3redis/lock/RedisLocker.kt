package com.example.springboot3redis.lock

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Lock
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.integration.redis.util.RedisLockRegistry

/**
 * @param connectionFactory The connection factory.
 * @param registryKey The key prefix for locks.
 * @param expireAfter The expiration in milliseconds.
 */
class RedisLocker(
    redisConnectionFactory: RedisConnectionFactory,
    registryKey: String,
    expireAfter: Long,
    private val timeout: Long,
) {

    private val redisLockRegistry = RedisLockRegistry(
        redisConnectionFactory,
        registryKey,
        expireAfter,
    )

    fun <T> run(
        lockKey: String,
        runnable: () -> T?,
    ) {
        var lock: Lock? = null
        try {
            lock = redisLockRegistry.obtain(lockKey)
            lock.tryLock(timeout, TimeUnit.SECONDS)
            runnable()
        } finally {
            lock?.unlock()
        }
    }
}

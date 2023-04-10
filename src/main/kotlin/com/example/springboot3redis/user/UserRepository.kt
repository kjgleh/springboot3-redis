package com.example.springboot3redis.user

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String>

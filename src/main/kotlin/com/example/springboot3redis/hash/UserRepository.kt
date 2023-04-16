package com.example.springboot3redis.hash

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String>

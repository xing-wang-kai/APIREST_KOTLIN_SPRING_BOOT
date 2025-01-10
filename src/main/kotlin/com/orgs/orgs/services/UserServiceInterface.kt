package com.orgs.orgs.services

import com.orgs.orgs.dao.UserDao
import com.orgs.orgs.dao.UserDto
import org.springframework.security.core.userdetails.UserDetailsService

interface UserServiceInterface: UserDetailsService {

    fun create(userDto: UserDto): UserDao

    fun getAll(): List<UserDao>

    fun getById(id: Long) : UserDao

    fun update(id: Long, userDto: UserDto): UserDao

    fun delete(id: Long)
}
package com.orgs.orgs.services

import com.orgs.orgs.dao.UserDao
import com.orgs.orgs.dao.UserDto
import com.orgs.orgs.models.User
import java.util.*

interface UserServiceInterface {

    fun create(userDto: UserDto): UserDao

    fun getAll(): List<UserDao>

    fun getById(id: Long) : UserDao

    fun update(id: Long, userDto: UserDto): UserDao

    fun delete(id: Long)
}
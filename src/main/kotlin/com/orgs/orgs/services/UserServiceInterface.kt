package com.orgs.orgs.services

import com.orgs.orgs.models.Product
import com.orgs.orgs.models.User
import java.util.*

interface UserServiceInterface {

    fun create(user: User): User

    fun getAll(): List<User>

    fun getById(id: Long) : Optional<User>

    fun update(id: Long, user: User): Optional<User>

    fun delete(id: Long)
}
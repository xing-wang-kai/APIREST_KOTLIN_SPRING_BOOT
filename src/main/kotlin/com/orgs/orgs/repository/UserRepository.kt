package com.orgs.orgs.repository

import com.orgs.orgs.models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Long> {
    fun findByIsDeleted(isDeleted: Boolean): List<User>
    fun findByIdAndIsDeletedFalse(id: Long): Optional<User>
}
package com.orgs.orgs.repository

import com.orgs.orgs.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
}
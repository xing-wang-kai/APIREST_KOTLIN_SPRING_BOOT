package com.orgs.orgs.dao

import com.orgs.orgs.models.Role
import java.time.LocalDateTime

class UserDao (
   var id: Long,
   var email: String,
   var name: String,
   var role: List<Role> = mutableListOf(),
   var password: String,
   var products: MutableList<Long>? = mutableListOf(),
   var createdAt: LocalDateTime = LocalDateTime.now(),
   var updatedAt: LocalDateTime? = null,
   var deletedAt: LocalDateTime? = null,
   var isDeleted: Boolean? = false,
   var isSyncronized: Boolean = false
)
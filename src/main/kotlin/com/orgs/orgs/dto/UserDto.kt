package com.orgs.orgs.dao

import java.time.LocalDateTime

class UserDto (
    var email: String,
    var name: String,
    var role: String,
    var password: String,
    var products: MutableList<Long>? = mutableListOf(),
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime? = null,
    var deletedAt: LocalDateTime? = null,
    var isDeleted: Boolean? = false,
    var isSycronized: Boolean = false
)
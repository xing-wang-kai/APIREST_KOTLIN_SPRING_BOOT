package com.orgs.orgs.extensions

import com.orgs.orgs.dao.UserDao
import com.orgs.orgs.dao.UserDto

import com.orgs.orgs.models.User


fun User.toUserDto(): UserDto {
    return UserDto(
        email = this.email,
        name = this.name,
        role = this.role,
        password = this.password,
        products = this.products?.map { it.id }?.toMutableList(),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
        isDeleted = this.isDeleted,
        isSycronized = this.isSycronized
    )
}
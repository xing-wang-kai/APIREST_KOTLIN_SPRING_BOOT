package com.orgs.orgs.extensions

import com.orgs.orgs.dao.UserDto
import com.orgs.orgs.models.Product
import com.orgs.orgs.models.User

fun UserDto.toUser(listOfProducts: MutableList<Product>): User {
    return User(
        email = this.email,
        name = this.name,
        role = this.role,
        password = this.password,
        products = listOfProducts,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
        isDeleted = this.isDeleted,
        isSycronized = this.isSycronized
    )
}
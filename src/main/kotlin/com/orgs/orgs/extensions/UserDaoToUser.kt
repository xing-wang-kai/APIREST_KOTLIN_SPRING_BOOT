package com.orgs.orgs.dao

import com.orgs.orgs.models.Product
import com.orgs.orgs.models.User

fun UserDao.toUser(listOfProducts: MutableList<Product>): User {
    return User(
        id = this.id!!,
        email = this.email,
        name = this.name,
        role = this.role,
        password = this.password,
        products =  listOfProducts,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
        isDeleted = this.isDeleted,
        isSycronized = this.isSycronized
    )
}
package com.orgs.orgs.dao

import com.orgs.orgs.models.User

// Método de extensão para converter Product em ProductDao
fun User.toUserDao(): UserDao {
    return UserDao(
        id = this.id!!,
        email = this.email,
        name = this.name,
        role = this.role,
        password = this.password,
        products = this.products?.map { it.id }?.toMutableList(),
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
        isDeleted = this.isDeleted,
        isSyncronized = this.isSyncronized
    )
}
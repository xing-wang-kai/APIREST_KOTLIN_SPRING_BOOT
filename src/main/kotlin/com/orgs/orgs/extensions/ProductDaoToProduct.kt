package com.orgs.orgs.dao

import com.orgs.orgs.models.Product
import com.orgs.orgs.models.User

// Método de extensão para converter Product em ProductDao
fun ProductDao.toProduct(user: User): Product {
    return Product(
        id = this.id,
        title = this.title,
        description = this.description,
        price = this.price,
        imgUrl = this.imgUrl,
        user = user,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
        isDeleted = this.isDeleted,
        isSycronized = this.isSycronized
    )
}

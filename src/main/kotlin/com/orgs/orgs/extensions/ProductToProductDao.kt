package com.orgs.orgs.dao

import com.orgs.orgs.models.Product

// Método de extensão para converter Product em ProductDao
fun Product.toProductDao(): ProductDao {
    return ProductDao(
        id = this.id,
        title = this.title,
        description = this.description,
        price = this.price,
        imgUrl = this.imgUrl,
        userId = this.user.id!!,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        deletedAt = this.deletedAt,
        isDeleted = this.isDeleted,
        isSycronized = this.isSycronized
    )
}

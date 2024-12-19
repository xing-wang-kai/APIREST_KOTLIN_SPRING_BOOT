package com.orgs.orgs.dto

import com.orgs.orgs.models.Product
import java.math.BigDecimal

data class ProductDTO(
    val id: Long,
    val title: String,
    val description: String,
    val price: BigDecimal,
    val imgUrl: String?,
    val userId: Long?
){
    fun Product.toDTO(): ProductDTO {
        return ProductDTO(
            id = this.id,
            title = this.title,
            description = this.description,
            price = this.price,
            imgUrl = this.imgUrl,
            userId = this.user?.id
        )
    }
}
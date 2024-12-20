package com.orgs.orgs.dto

import com.orgs.orgs.models.Product
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import org.springframework.stereotype.Component

import java.math.BigDecimal

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of=["id"])
data class ProductDTO(
    val id: Long,
    @field:NotEmpty @field:Size(min = 5, max = 100) val title: String,
    @field:NotEmpty @field:Size(min = 5, max = 500) val description: String,
    @field:NotNull val price: BigDecimal,
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
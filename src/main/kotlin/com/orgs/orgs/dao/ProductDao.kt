package com.orgs.orgs.dao

import java.math.BigDecimal
import java.time.LocalDateTime

class ProductDao(val id: Long,
                 val title: String,
                 val description: String,
                 val price: BigDecimal,
                 val imgUrl: String?,
                 val userId: Long,
                 val createdAt: LocalDateTime,
                 val updatedAt: LocalDateTime?,
                 val deletedAt: LocalDateTime?,
                 val isDeleted: Boolean,
                 val isSycronized: Boolean
)
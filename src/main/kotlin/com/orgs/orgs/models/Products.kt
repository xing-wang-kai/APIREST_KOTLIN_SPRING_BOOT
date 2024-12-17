package com.orgs.orgs.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.math.BigDecimal


@Entity(name = "products")
data class Product(
    @Id @GeneratedValue var id: Long = 0L,
    @Column(name="title") var title: String,
    @Column(name="description") var description: String,
    @Column(name="price") var price: BigDecimal,
    @Column(name="img_url") var imgUrl: String?,
    @Column(name="user_id") var userId: Long? = 0L
)
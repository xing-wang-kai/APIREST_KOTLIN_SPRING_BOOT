package com.orgs.orgs.models


import java.math.BigDecimal
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity(name = "products")
data class Product(
    @Id @GeneratedValue var id: Long = 0L,
    @Column(name="title") var title: String,
    @Column(name="description") var description: String,
    @Column(name="price") var price: BigDecimal,
    @Column(name="img_url") var imgUrl: String?,
    @Column(name="user_id") var userId: Long? = 0L
)

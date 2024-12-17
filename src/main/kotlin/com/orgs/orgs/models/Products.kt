package com.orgs.orgs.models

import jakarta.persistence.*
import java.math.BigDecimal


@Entity
@Table(name = "products")
data class Product(
    @Id @GeneratedValue var id: Long = 0L,
    @Column(name="title") var title: String,
    @Column(name="description") var description: String,
    @Column(name="price") var price: BigDecimal,
    @Column(name="img_url") var imgUrl: String? = null,
    @Column(name="user_id") var userId: Long? = 0L
) {
    // Construtor sem argumentos para o Hibernate
    constructor() : this(0L, "", "", BigDecimal.ZERO, null, 0L)
}
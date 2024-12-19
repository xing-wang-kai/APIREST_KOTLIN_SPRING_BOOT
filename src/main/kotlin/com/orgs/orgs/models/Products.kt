package com.orgs.orgs.models

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import java.math.BigDecimal


@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of=["id"])
data class Product(
    @Id @GeneratedValue var id: Long = 0L,
    @Column(name="title") var title: String,
    @Column(name="description") var description: String,
    @Column(name="price") var price: BigDecimal,
    @Column(name="img_url") var imgUrl: String? = null,
    @ManyToOne @JoinColumn(name = "user_id") var user: User? = null
) {
    constructor() : this(0L, "", "", BigDecimal.ZERO, null, null)

}
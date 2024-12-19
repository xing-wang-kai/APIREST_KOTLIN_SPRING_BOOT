package com.orgs.orgs.models

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import java.math.BigDecimal
import java.time.LocalDateTime


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
    @ManyToOne @JoinColumn(name = "user_id") var user: User,

    @Column(name="created_at") var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name="updated_at") var updatedAt: LocalDateTime? = null,
    @Column(name="deleted_at") var deletedAt: LocalDateTime? = null,
    @Column(name="is_deleted") var isDeleted: Boolean = false,
    @Column(name="is_sycronized") var isSycronized: Boolean = false
){
    override fun toString(): String {
        return "Product(id=$id, title=$title, description=$description, price=$price, imgUrl=$imgUrl, createdAt=$createdAt, updatedAt=$updatedAt, deletedAt=$deletedAt)"
    }
}
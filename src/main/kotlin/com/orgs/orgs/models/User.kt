package com.orgs.orgs.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of=["id"])
data class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    @Column(name="email") var email: String,
    @Column(name="name") var name: String,
    @Column(name="role") var role: String,
    @Column(name="password") var password: String,
    @OneToMany(mappedBy = "user")
    var products: MutableList<Product>? = mutableListOf(),

    @Column(name="created_at") var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name="updated_at") var updatedAt: LocalDateTime? = null,
    @Column(name="deleted_at") var deletedAt: LocalDateTime? = null,
    @Column(name="is_deleted") var isDeleted: Boolean? = false,
    @Column(name="is_syncronized") var isSyncronized: Boolean = false
)
{
    override fun toString(): String {
        return "User(id=$id, email=$email, name=$name, role=$role, createdAt=$createdAt, updatedAt=$updatedAt, deletedAt=$deletedAt)"
    }
}
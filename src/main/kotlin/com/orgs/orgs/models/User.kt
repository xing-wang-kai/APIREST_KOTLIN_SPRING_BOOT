package com.orgs.orgs.models

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of=["id"])
data class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(name="email") var email: String,
    @Column(name="name") var name: String,
    @Column(name="role") var role: String,
    @Column(name="password") var password: String,
    @OneToMany(mappedBy = "user")
    var products: List<Product>? = ArrayList<Product>()
) {
    constructor() : this(0L, "", "", "", "", null)
}

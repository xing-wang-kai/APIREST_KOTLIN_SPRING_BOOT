package com.orgs.orgs.models

import jakarta.persistence.*

@Entity
@Table(name="users")
class User (
    @Id @GeneratedValue var id: Long,
    @Column(name="email") var email: String,
    @Column(name="name") var name: String,
    @Column(name="role") var role: String,
    @Column(name="password") var password: String,
    @OneToMany(mappedBy = "user")
    var products: List<Product> = emptyList()
) {
    constructor() : this(0L, "", "", "", "", emptyList())
}

package com.orgs.orgs.models

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority

@Entity
@Table(name="role")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of=["id"])
data class Role (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column(name = "nome") var name: String
): GrantedAuthority {
    override fun getAuthority() = name
}

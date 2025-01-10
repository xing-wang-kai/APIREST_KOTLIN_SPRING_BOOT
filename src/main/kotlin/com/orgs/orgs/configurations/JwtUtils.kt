package com.orgs.orgs.configurations

import com.orgs.orgs.services.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import java.util.Date
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

@Component
class JwtUtils(
    private val userService: UserService
) {

    private val expiration: Long = 60000

    @Value("\${jwt.secret}")
    private lateinit var secrets: String

    fun generateToken(username: String, authorities: MutableCollection<out GrantedAuthority>): String? {
        return Jwts.builder()
            .setSubject(username)
            .claim("role", authorities)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secrets.toByteArray())
            .compact()
    }

    fun isValid(jwt: String?): Boolean {


        return try {

            if (jwt?.split(".")?.size != 3) {
                throw MalformedJwtException("JWT strings must contain exactly 2 period characters. ${jwt}")
            }
            Jwts.parser()
                .setSigningKey(secrets.toByteArray())
                .parseClaimsJws(jwt)
            true
        }catch (e: IllegalArgumentException){
            false
        }
    }

    fun getAuthentication(jwt: String?): Authentication {
        var username = Jwts.parser()
            .setSigningKey(secrets.toByteArray())
            .parseClaimsJws(jwt).body.subject

        val user = userService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }
}
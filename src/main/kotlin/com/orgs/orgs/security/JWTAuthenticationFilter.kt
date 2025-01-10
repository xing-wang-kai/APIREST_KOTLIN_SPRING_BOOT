package com.orgs.orgs.security

import com.orgs.orgs.configurations.JwtUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JWTAuthenticationFilter(private val jwtUtil: JwtUtils) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")
        val jwt = getTokenDetails(token)

        if(jwtUtil.isValid(jwt)){
            val autentication = jwtUtil.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = autentication
            filterChain.doFilter(request, response)
        }

    }

    private fun getTokenDetails(token: String?): String? {
        return token?.let { jwt ->
            jwt.startsWith("Bearer ")
            jwt.substring(7, jwt.length)
        }
    }
}

package com.sorsix.backend.authentication.filters

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(private val authenticationManager: AuthenticationManager) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        val jwt: String = request.getHeader("Authorization")

        try {
            val authentication = BearerTokenAuthenticationToken(jwt)
            val authResult = authenticationManager.authenticate(authentication)
            SecurityContextHolder.getContext().authentication = authResult
        } catch (e: Exception) {
            response.status = 401
            return
        }

        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.servletPath.equals("/api/login") || request.servletPath.equals("/api/register")
    }
}

package com.sorsix.backend.config

import com.sorsix.backend.authentication.authenticationProvider.CustomAuthenticationProvider
import com.sorsix.backend.authentication.filters.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val customAuthenticationProvider: CustomAuthenticationProvider
) {

    @Bean
    fun authenticationManager(): AuthenticationManager {
        return ProviderManager(listOf(customAuthenticationProvider))
    }

    @Bean
    fun jwtAuthenticationFilter(): JwtAuthenticationFilter{
        return JwtAuthenticationFilter(authenticationManager())
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.securityMatcher("/api/**")
            .authorizeHttpRequests {rmr -> rmr
                .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/register").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
            }
            .cors {it.disable()}
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .csrf { it.disable() }

        http.oauth2ResourceServer { oauth2 ->
            oauth2
                .jwt(Customizer.withDefaults())
        }

        http.addFilterAt(jwtAuthenticationFilter(), BasicAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:4200", "http://localhost:8080")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
        configuration.allowedHeaders = listOf("authorization", "content-type")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
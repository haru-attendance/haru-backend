package com.haru.attendance.config

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authz ->
                authz.requestMatchers("/**")
                    .permitAll()
                    .requestMatchers(PathRequest.toH2Console())
                    .permitAll()
                    .anyRequest().authenticated()
            }
            .csrf { it.ignoringRequestMatchers(PathRequest.toH2Console()).disable() }
            .headers { it.frameOptions { frameOption -> frameOption.disable() } }
            .httpBasic(Customizer.withDefaults())
        return http.build()
    }
}

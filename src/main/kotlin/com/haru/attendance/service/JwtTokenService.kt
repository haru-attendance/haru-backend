package com.haru.attendance.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtTokenService(
    @Value("\${jwt.expiration-time}")
    private val expirationTime: Long,
    @Value("\${jwt.secret-key}")
    private val secretKey: String
) {
    fun generateToken(userDetails: UserDetails): String {
        return generateToken(emptyMap(), userDetails)
    }

    fun extractUsername(token: String): String? {
        return extractClaims(token, Claims::getSubject)
    }

    private fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String {
        return Jwts.builder()
            .claims(extraClaims)
            .subject(userDetails.username)
            .signWith(getSigningKey())
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expirationTime))
            .compact()
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        val isExpired = !isTokenExpired(token)
        val result = username == userDetails.username && isExpired
        return result
    }

    private fun isTokenExpired(token: String): Boolean {
        val before = extractClaims(token, Claims::getExpiration)
            ?.before(Date())
        return before ?: true
    }

    private fun extractAllClaims(token: String) = Jwts.parser()
        .verifyWith(getSigningKey())
        .build()
        .parseSignedClaims(token)
        .payload

    private fun getSigningKey(): SecretKey {
        val keyBytes = Base64.getDecoder().decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    private fun <T> extractClaims(token: String, claimsResolver: (claims: Claims) -> T): T? {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

}
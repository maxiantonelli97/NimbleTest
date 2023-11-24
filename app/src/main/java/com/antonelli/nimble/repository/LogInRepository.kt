package com.antonelli.nimble.repository

interface LogInRepository {
    suspend fun logIn(email: String, pass: String): Boolean
    suspend fun refreshToken(): Boolean
}

package com.antonelli.nimble.repository

interface AuthRepository {
    suspend fun getToken(): String?
    suspend fun setToken(token: String?)
    suspend fun setValid(valid: Double?)
    suspend fun isTokenValido(): Boolean
}

package com.antonelli.nimble.repository

import android.content.Context
import com.securepreferences.SecurePreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val context: Context
) : AuthRepository {

    private val preferences by lazy { SecurePreferences(context.applicationContext) }

    override suspend fun getToken(): String? {
        return preferences.getString("token", null)
    }

    override suspend fun setToken(token: String?) {
        preferences.edit().putString("token", "Bearer $token").apply()
    }

    override suspend fun setValid(valid: Double?) {
        preferences.edit().putLong("valid", (valid ?: 0).toLong()).apply()
    }

    override suspend fun isTokenValido(): Boolean {
        return try {
            val aux1 = System.currentTimeMillis() / 1000
            val aux2 = preferences.getLong("valid", -1)
            val result = aux2 > aux1
            result
        } catch (e: Exception) {
            false
        }
    }
}

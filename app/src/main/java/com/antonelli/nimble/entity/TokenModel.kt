package com.antonelli.nimble.entity

data class TokenModel(
    val access_token: String? = null,
    val token_type: String? = null,
    val expires_in: Double? = null,
    val refresh_token: String? = null,
    val created_at: Double? = null
)

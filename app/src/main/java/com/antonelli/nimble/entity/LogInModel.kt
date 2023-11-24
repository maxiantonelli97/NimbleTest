package com.antonelli.nimble.entity

import com.antonelli.nimble.BuildConfig

data class LogInModel(
    var grant_type: String? = null,
    var email: String? = null,
    var password: String? = null,
    val client_id: String = BuildConfig.CLIENT_ID,
    val client_secret: String = BuildConfig.SECRET_ID
)

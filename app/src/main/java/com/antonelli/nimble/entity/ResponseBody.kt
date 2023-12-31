package com.antonelli.nimble.entity

data class ResponseBody<R>(
    val data: R? = null,
    val auth: Boolean = true
)

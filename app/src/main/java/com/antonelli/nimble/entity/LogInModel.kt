package com.antonelli.nimble.entity

data class LogInModel(
    var grant_type: String? = null,
    var email: String? = null,
    var password: String? = null,
    val client_id: String = "ofzl-2h5ympKa0WqqTzqlVJUiRsxmXQmt5tkgrlWnOE",
    val client_secret: String = "lMQb900L-mTeU-FVTCwyhjsfBwRCxwwbCitPob96cuU"
    // TODO extraer estos strings sensibles
)

package com.antonelli.nimble.entity

data class LogInModel(
    val grant_type: String = "password",
    val email: String? = null,
    val password: String? = null,
    val client_id: String = "H701y6E76KGmIaWC1C-ZgSGLsBbyA0ubCIRr1xk1Ckg",
    val client_secret: String = "X3ewlaFw5a4r0nKbpyXxP6ofMlZ9y8aRSu_FL1smyxg"
    // TODO extraer estos strings sensibles
)

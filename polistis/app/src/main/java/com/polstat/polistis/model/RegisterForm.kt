package com.polstat.polistis.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterForm(
    val nama: String,
    val email: String,
    val password: String,
    val role: String?
)

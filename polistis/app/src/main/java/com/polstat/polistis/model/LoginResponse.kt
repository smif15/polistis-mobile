package com.polstat.polistis.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse (
    val email: String,
    val accessToken: String
)
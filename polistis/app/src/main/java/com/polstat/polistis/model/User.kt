package com.polstat.polistis.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long?,
    val email: String,
    val password: String,
    val role: Role?
)

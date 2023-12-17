package com.polstat.polistis.model

import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse (
    val message: String,
    val status: String
)


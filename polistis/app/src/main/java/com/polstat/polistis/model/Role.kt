package com.polstat.polistis.model

import kotlinx.serialization.Serializable

@Serializable
data class Role(
    val id: Long?,
    val namaRole : String
)

package com.polstat.polistis.model

import kotlinx.serialization.Serializable

@Serializable
data class DokterDto(
    val id : Long? = null,
    val userDto: User? = null,
    val nama : String,
    val nomorTelepon : String,
    val riwayatPekerjaan : String
)

package com.polstat.polistis.model

import kotlinx.serialization.Serializable

@Serializable
data class PasienDto(
    val id: Long? = null,
    val nama: String?,
    val alergi: String?,
    val riwayatPenyakit: String?,
    val userDto: User? = null
)

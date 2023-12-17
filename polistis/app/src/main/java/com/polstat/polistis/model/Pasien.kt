package com.polstat.polistis.model

import kotlinx.serialization.Serializable

@Serializable
data class Pasien (
    val nama: String,
    val email: String,
    val alergi: String?,
    val riwayatPenyakit: String?
)
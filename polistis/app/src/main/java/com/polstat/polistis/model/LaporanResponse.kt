package com.polstat.polistis.model

import kotlinx.serialization.Serializable

@Serializable
data class LaporanResponse(
    val namaPasien: String,
    val keluhan: String,
    val namaDokter: String,
    val catatanDokter: String,
    val dibuatPada: String
)

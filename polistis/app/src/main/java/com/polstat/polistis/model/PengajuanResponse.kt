package com.polstat.polistis.model

import kotlinx.serialization.Serializable

@Serializable
data class PengajuanResponse(
    val id: Long,
    val waktu: String,
    val keluhan: String,
    val dibuatPada: String,
    val status: String,
    val namaPasien: String,
    val namaDokter: String
)

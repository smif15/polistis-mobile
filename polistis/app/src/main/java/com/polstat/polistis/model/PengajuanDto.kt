package com.polstat.polistis.model

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class PengajuanDto(
    val id: Long? = null,
    val waktu: String,
    val keluhan: String,
    val dibuatPada: Date? = null,
    val updatePada: Date? = null,
    val status: String? = null,
    val pasien: PasienDto? = null,
    val dokter: DokterDto? = null
)

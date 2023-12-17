package com.polstat.polistis.model

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class LaporanDto(
    val id: Long? = null,
    val keluhan: String? = null,
    val catatanDokter: String,
    val dibuatPada: Date? = null,
    val pasien: PasienDto? = null,
    val dokter: DokterDto? = null

    )

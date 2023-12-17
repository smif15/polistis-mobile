package com.polstat.polistis.model

import kotlinx.serialization.Serializable

@Serializable
data class Laporan(
    val id : Long,
    val catatanDokter : String,
    val keluhan : String,
    val dokterId : Long,
    val pasienId : Long
)

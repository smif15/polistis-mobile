package com.polstat.polistis.service

import com.polstat.polistis.model.LaporanDto
import com.polstat.polistis.model.LaporanResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface LaporanService {
    @POST("/jadwal/{idPengajuan}/laporan")
    suspend fun buatLaporan(@Header("Authorization") token: String,
                            @Path("idPengajuan") idPengajuan: Long,
                            @Body laporanDto: LaporanDto
    )

    @GET("/laporanpasien")
    suspend fun tampilLaporanPasien(@Header("Authorization") token: String) : List<LaporanResponse>

    @GET("/laporandokter")
    suspend fun tampilLaporanDokter(@Header("Authorization") token: String) : List<LaporanResponse>
}
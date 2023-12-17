package com.polstat.polistis.service

import com.polstat.polistis.model.PengajuanDto
import com.polstat.polistis.model.PengajuanResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface PengajuanService {
    @POST("/buatpengajuan")
    suspend fun buatPengajuan(@Header("Authorization") token: String,
                              @Body form: PengajuanDto
    )

    @GET("/pengajuan")
    suspend fun tampilSemuaPengajuan(@Header("Authorization") token: String) : List<PengajuanResponse>

    @PATCH("/pengajuan/{idPengajuan}/setuju/{idDokter}")
    suspend fun setujuiPengajuan(@Header("Authorization") token: String,
                                 @Path("idPengajuan") idPengajuan: Long,
                                 @Path("idDokter") idDokter: Long)

    @DELETE("/pengajuan/{idPengajuan}/tolak")
    suspend fun tolakPengajuan(@Header("Authorization") token: String,
                               @Path("idPengajuan") idPengajuan: Long)

    @GET("/jadwal")
    suspend fun tampilJadwal(@Header("Authorization") token: String) : List<PengajuanResponse>

    @GET("/jadwal/{idPengajuan}")
    suspend fun tampilFormLaporan(@Header("Authorization") token: String,
                                  @Path("idPengajuan") idPengajuan: Long) : PengajuanResponse


}
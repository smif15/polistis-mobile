package com.polstat.polistis.data

import com.polstat.polistis.model.LoginForm
import com.polstat.polistis.model.LoginResponse
import com.polstat.polistis.model.MessageResponse
import com.polstat.polistis.model.Pasien
import com.polstat.polistis.model.PasienDto
import com.polstat.polistis.model.PengajuanDto
import com.polstat.polistis.model.PengajuanResponse
import com.polstat.polistis.model.RegisterForm
import com.polstat.polistis.model.User
import com.polstat.polistis.service.PengajuanService
import com.polstat.polistis.service.UserService

interface PengajuanRepository {
    suspend fun buatPengajuan(token: String, waktu: String, keluhan: String)
    suspend fun tampilSemuaPengajuan(token: String): List<PengajuanResponse>
    suspend fun setujuiPengajuan(token: String, idPengajuan: Long, idDokter: Long)
    suspend fun tolakPengajuan(token: String, idPengajuan: Long)
    suspend fun tampilJadwal(token: String) : List<PengajuanResponse>
    suspend fun tampilFormLaporan(token: String, idPengajuan: Long): PengajuanResponse
}

class PengajuanRepositoryImpl(private val pengajuanService: PengajuanService) : PengajuanRepository {

    override suspend fun buatPengajuan(token: String, waktu: String, keluhan: String) =
        pengajuanService.buatPengajuan(token = "Bearer $token", PengajuanDto(
            waktu = waktu, keluhan = keluhan
        )
        )

    override suspend fun tampilSemuaPengajuan(token: String): List<PengajuanResponse> =
        pengajuanService.tampilSemuaPengajuan(token = "Bearer $token")

    override suspend fun setujuiPengajuan(token: String, idPengajuan: Long, idDokter: Long) =
        pengajuanService.setujuiPengajuan(token = "Bearer $token", idPengajuan, idDokter)

    override suspend fun tolakPengajuan(token: String, idPengajuan: Long) =
        pengajuanService.tolakPengajuan(token = "Bearer $token", idPengajuan)

    override suspend fun tampilJadwal(token: String) =
        pengajuanService.tampilJadwal(token = "Bearer $token")

    override suspend fun tampilFormLaporan(token: String, idPengajuan: Long) =
        pengajuanService.tampilFormLaporan(token = "Bearer $token", idPengajuan)
}
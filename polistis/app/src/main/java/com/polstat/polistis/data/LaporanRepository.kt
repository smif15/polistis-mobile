package com.polstat.polistis.data

import com.polstat.polistis.model.LaporanDto
import com.polstat.polistis.model.LaporanResponse
import com.polstat.polistis.service.LaporanService

interface LaporanRepository{
    suspend fun buatLaporan(token: String, idPengajuan: Long, catatanDokter : String)
    suspend fun tampilLaporanPasien(token: String) : List<LaporanResponse>
    suspend fun tampilLaporanDokter(token: String) : List<LaporanResponse>
}

class LaporanRepositoryImpl(private val laporanService: LaporanService) : LaporanRepository {
    override suspend fun buatLaporan(token: String, idPengajuan: Long, catatanDokter: String) =
        laporanService.buatLaporan(token = "Bearer $token",
            idPengajuan = idPengajuan,
            LaporanDto(catatanDokter = catatanDokter)
        )

    override suspend fun tampilLaporanPasien(token: String): List<LaporanResponse> =
        laporanService.tampilLaporanPasien(token = "Bearer $token")

    override suspend fun tampilLaporanDokter(token: String): List<LaporanResponse> =
        laporanService.tampilLaporanDokter(token = "Bearer $token")

}
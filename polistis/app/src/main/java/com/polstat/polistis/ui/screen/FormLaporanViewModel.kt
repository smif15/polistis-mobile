package com.polstat.polistis.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.polstat.polistis.PolistisApplication
import com.polstat.polistis.data.LaporanRepository
import com.polstat.polistis.data.PengajuanRepository
import com.polstat.polistis.model.PengajuanResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LaporanVM(
    private val pengajuanRepository: PengajuanRepository,
    private val laporanRepository: LaporanRepository,
) : ViewModel() {
    private lateinit var pengajuan: PengajuanResponse
    private lateinit var token: String
    private var pengajuanId: Long = 0

    private val _pengajuanData = MutableStateFlow<PengajuanResponse?>(null)
    val pengajuanData: StateFlow<PengajuanResponse?> get() = _pengajuanData.asStateFlow()
    var catatanDokter by mutableStateOf("")
        private set

    fun setToken(token: String) {
        this.token = token
    }

    fun setPengajuanId(id: Long) {
        this.pengajuanId = id
    }

    fun ambil() {
        viewModelScope.launch {
            pengajuan = pengajuanRepository.tampilFormLaporan(token, pengajuanId)
            _pengajuanData.value = pengajuan
        }
    }

    fun updateCatatanDokter(catatan: String) {
        catatanDokter = catatan
    }

    suspend fun buatLaporan() : BuatLaporanResult {
        try {
            laporanRepository.buatLaporan(token, idPengajuan = pengajuanId,
                catatanDokter)
            return BuatLaporanResult.Success
        } catch (e: Exception) {
            println("eror di buatLaporan?")
            println(e)
            return BuatLaporanResult.Error
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PolistisApplication)
                val pengajuanRepository = application.container.pengajuanRepository
                val laporanRepository = application.container.laporanRepository
                LaporanVM(
                    pengajuanRepository, laporanRepository
                )
            }
        }
    }

}

enum class BuatLaporanResult {
    Success,
    Error
}
package com.polstat.polistis.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.polstat.polistis.PolistisApplication
import com.polstat.polistis.data.PengajuanRepository

class FormDaftarViewModel(
    private val pengajuanRepository: PengajuanRepository
) : ViewModel() {
    var keluhan by mutableStateOf("")
        private set
    var waktu by mutableStateOf("")
        private set
    private lateinit var token: String

    fun setToken(token: String) {
        this.token = token
    }

    fun updateKeluhan(keluhan: String) {
        this.keluhan = keluhan
    }

    fun updateWaktu(waktu: String) {
        this.waktu = waktu
    }

    suspend fun buatPengajuan(): BuatProfilResult {
        try {
            pengajuanRepository.buatPengajuan(
                token, waktu, keluhan
            )
            return BuatProfilResult.Success
        } catch(e: Exception) {
            println(e)
            return BuatProfilResult.Error
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PolistisApplication)
                val pengajuanRepository = application.container.pengajuanRepository
                FormDaftarViewModel(
                    pengajuanRepository = pengajuanRepository
                )
            }
        }
    }

}

enum class BuatProfilResult {
    Success,
    Error
}
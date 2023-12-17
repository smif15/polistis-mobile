package com.polstat.polistis.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.polstat.polistis.PolistisApplication
import com.polstat.polistis.data.UserPreferencesRepository
import com.polstat.polistis.data.UserRepository
import com.polstat.polistis.model.Pasien
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FormProfilViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var nama by mutableStateOf("")
        private set
    var alergi by mutableStateOf("")
        private set
    var riwayatPenyakit by mutableStateOf("")
        private set
    private lateinit var token: String

    fun setToken(token: String) {
        this.token = token
    }

    fun updateNama(nama: String) {
        this.nama = nama
    }

    fun updateAlergi(alergi: String) {
        this.alergi = alergi
    }

    fun updateRiwayatPenyakit(riwayatPenyakit: String) {
        this.riwayatPenyakit = riwayatPenyakit
    }

    suspend fun updateProfilPasien(): UpdateProfilResult {
        try {
            userRepository.updateProfilPasien(
                token, nama, alergi, riwayatPenyakit
            )
            return UpdateProfilResult.Success
        } catch (e:Exception) {
            println(e)
            return UpdateProfilResult.Error
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PolistisApplication)
                val userRepository = application.container.userRepository
                FormProfilViewModel(
                    userRepository = userRepository
                )
            }
        }
    }
}

enum class UpdateProfilResult {
    Success,
    Error
}
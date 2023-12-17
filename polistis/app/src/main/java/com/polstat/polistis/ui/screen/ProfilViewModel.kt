package com.polstat.polistis.ui.screen

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
import kotlinx.coroutines.launch

class ProfilViewModel(
    private val userRepository: UserRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
) : ViewModel() {
    private lateinit var pasien: Pasien

    private val _pasienData = MutableStateFlow<Pasien?>(null)
    val pasienData: StateFlow<Pasien?> get() = _pasienData.asStateFlow()

    fun getInfoPasien(token: String) {
        viewModelScope.launch {
            pasien = userRepository.getInfoPasien(token)
            _pasienData.value = pasien
        }
    }

    suspend fun logout() {
        userPreferencesRepository.saveToken("")
        userPreferencesRepository.saveEmail("")
        userPreferencesRepository.saveIsPasien(false)
        userPreferencesRepository.saveIsDokter(false)
        userPreferencesRepository.saveIsAdmin(false)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PolistisApplication)
                val userRepository = application.container.userRepository
                ProfilViewModel(
                    userPreferencesRepository = application.userPreferenceRepository,
                    userRepository = userRepository
                )
            }
        }
    }
}
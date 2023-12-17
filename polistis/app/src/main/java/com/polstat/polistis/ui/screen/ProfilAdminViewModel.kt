package com.polstat.polistis.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

class ProfilAdminViewModel(
    private val userRepository: UserRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
) : ViewModel() {

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
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PolistisApplication)
                val userRepository = application.container.userRepository
                ProfilAdminViewModel(
                    userPreferencesRepository = application.userPreferenceRepository,
                    userRepository = userRepository
                )
            }
        }
    }
}
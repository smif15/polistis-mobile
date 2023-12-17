package com.polstat.polistis.ui.screen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.polstat.polistis.PolistisApplication
import com.polstat.polistis.data.UserPreferencesRepository
import com.polstat.polistis.data.UserRepository
import com.polstat.polistis.model.LoginForm

enum class LoginResult{
    Success,
    Error
}
class LoginViewModel(
    private val userRepository: UserRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    fun updateEmail(email: String) {
        this.email = email
    }

    fun updatePassword(password: String) {
        this.password = password
    }

    suspend fun attemptLogin(): LoginResult {
        try{
            val loginResponse = userRepository.login(LoginForm(email, password))
            Log.d(TAG, "accessToken: ${loginResponse.accessToken}")

            userPreferencesRepository.saveToken(loginResponse.accessToken)

            val user = userRepository.getProfil(loginResponse.accessToken)
            val isPasien = { user.role?.id == 1L }()
            val isDokter = { user.role?.id == 2L }()
            val isAdmin = { user.role?.id == 3L }()

            Log.d(TAG, "email: ${user.email}")
            Log.d(TAG, "isPasien: $isPasien")
            Log.d(TAG, "isDokter: $isDokter")
            Log.d(TAG, "isAdmin: $isAdmin")

            userPreferencesRepository.saveEmail(user.email)
            userPreferencesRepository.saveIsAdmin(isAdmin)
            userPreferencesRepository.saveIsDokter(isDokter)
            userPreferencesRepository.saveIsPasien(isPasien)

            return LoginResult.Success
        } catch(e: Exception) {
            println(e)
            println("Error di login")
            return LoginResult.Error
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PolistisApplication)
                val userRepository = application.container.userRepository
                LoginViewModel(
                    userPreferencesRepository = application.userPreferenceRepository,
                    userRepository = userRepository
                )
            }
        }
    }

}
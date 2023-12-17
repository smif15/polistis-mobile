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
import com.polstat.polistis.data.UserRepository
import com.polstat.polistis.model.RegisterForm

enum class RegisterResult{
    Success,
    Empty,
    NetworkError
}

class RegisterViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var nama by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun updateNama(nama: String) {
        this.nama = nama
    }

    fun updateEmail(email: String) {
        this.email = email
    }

    fun updatePassword(password: String) {
        this.password = password
    }

    suspend fun register(): RegisterResult {
        try {
            println("Halo")
            if(nama == "" || password == "" || email == "") {
                println("Empty")
                return RegisterResult.Empty
            }
            userRepository.register(RegisterForm(nama, email, password, null))
            println("Success")
            return RegisterResult.Success
        } catch(e: Exception){
            return RegisterResult.NetworkError
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PolistisApplication)
                val userRepository = application.container.userRepository
                RegisterViewModel(userRepository = userRepository)
            }
        }
    }

}




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
import com.polstat.polistis.data.PengajuanRepository
import com.polstat.polistis.model.PengajuanResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeDokterViewModel (
    private val pengajuanRepository: PengajuanRepository
) : ViewModel() {
    private lateinit var pengajuans : List<PengajuanResponse>

    private val _pengajuansListData = MutableStateFlow<List<PengajuanResponse>?>(null)
    val pengajuansList: StateFlow<List<PengajuanResponse>?> get() = _pengajuansListData.asStateFlow()

    var selectedId: Long by mutableStateOf(0)
    private lateinit var token: String

    fun setToken(token: String) {
        this.token = token
    }

    fun getPengajuans() {
        viewModelScope.launch {
            try {
                _pengajuansListData.value = pengajuanRepository.tampilJadwal(token)
            } catch(e: Exception) {
                println(e)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PolistisApplication)
                val pengajuanRepository = application.container.pengajuanRepository
                HomeDokterViewModel(
                    pengajuanRepository = pengajuanRepository
                )
            }
        }
    }
}
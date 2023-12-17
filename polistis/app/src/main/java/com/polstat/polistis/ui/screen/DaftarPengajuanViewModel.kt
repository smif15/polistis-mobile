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
import com.polstat.polistis.model.PengajuanDto
import com.polstat.polistis.model.PengajuanResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class DaftarPengajuanViewModel(
    private val pengajuanRepository: PengajuanRepository
) : ViewModel() {
    private lateinit var pengajuans : List<PengajuanResponse>

    private val _pengajuansListData = MutableStateFlow<List<PengajuanResponse>?>(null)
    val pengajuansList: StateFlow<List<PengajuanResponse>?> get() = _pengajuansListData.asStateFlow()

    var daftarPengajuanUiState: DaftarPengajuanUiState by mutableStateOf(DaftarPengajuanUiState.Loading)
        private set

    var selectedId: Long by mutableStateOf(0)
    private lateinit var token: String

    fun setToken(token: String) {
        this.token = token
    }

    fun setuju() {
        viewModelScope.launch {
            try{
                pengajuanRepository.setujuiPengajuan(token, idPengajuan = selectedId, idDokter = 1)
            } catch(e: Exception) {
                println(e)
            }
        }
    }

    fun tolak() {
        viewModelScope.launch {
            try{
                pengajuanRepository.tolakPengajuan(token, idPengajuan = selectedId)
            } catch (e: Exception) {
                println(e)
            }
        }
    }


    fun getAllPengajuan() {
        viewModelScope.launch {
            try {
                _pengajuansListData.value = pengajuanRepository.tampilSemuaPengajuan(token)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PolistisApplication)
                val pengajuanRepository = application.container.pengajuanRepository
                DaftarPengajuanViewModel(
                    pengajuanRepository = pengajuanRepository
                )
            }
        }
    }

}

sealed interface DaftarPengajuanUiState{
    data class Success(val pengajuans: List<PengajuanResponse>) : DaftarPengajuanUiState
    object Loading: DaftarPengajuanUiState
    object Error: DaftarPengajuanUiState
}
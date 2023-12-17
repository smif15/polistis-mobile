package com.polstat.polistis.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.polstat.polistis.PolistisApplication
import com.polstat.polistis.data.LaporanRepository
import com.polstat.polistis.model.LaporanResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RiwayatDokterViewModel (
    private val laporanRepository: LaporanRepository
): ViewModel() {
    private lateinit var laporans : List<LaporanResponse>

    private val _laporansListData = MutableStateFlow<List<LaporanResponse>?>(null)
    val laporansList: StateFlow<List<LaporanResponse>?> get() = _laporansListData.asStateFlow()

    private lateinit var token: String

    fun setToken(token: String) {
        this.token = token
    }

    fun getDaftarPengajuanDokter() {
        viewModelScope.launch {
            try {
                _laporansListData.value = laporanRepository.tampilLaporanDokter(token)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PolistisApplication)
                val laporanRepository = application.container.laporanRepository
                RiwayatDokterViewModel(
                    laporanRepository = laporanRepository
                )
            }
        }
    }
}
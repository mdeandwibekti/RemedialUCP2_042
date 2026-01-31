package com.example.remedialucp2_042.tampilan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remedialucp2_042.data.Repository.BukuRepository
import com.example.remedialucp2_042.data.local.entity.Kategori
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BukuViewModel(private val repository: BukuRepository) : ViewModel() {

    private val _statusMessage = MutableStateFlow<String?>(null)
    val statusMessage: StateFlow<String?> = _statusMessage

    fun processDeleteKategori(kategori: Kategori, hapusPermanen: Boolean) {
        viewModelScope.launch {
            try {
                repository.deleteKategoriSecure(kategori, hapusPermanen)
                _statusMessage.value = "Berhasil menghapus kategori."
            } catch (e: Exception) {
                _statusMessage.value = "Kesalahan: ${e.message}"
            }
        }
    }

    fun searchBukuByKategori(katId: Long) {
        viewModelScope.launch {
            val results = repository.getBukuRecursive(katId)
        }
    }
}
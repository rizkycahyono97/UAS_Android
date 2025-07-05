package com.example.kos_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kos_android.data.model.FilterKos
import com.example.kos_android.data.model.Kost
import com.example.kos_android.repository.KostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class KostViewModel(
    private val kostRepository: KostRepository
): ViewModel() {
    //mmenyimpan data view model
    private val _kostList = MutableLiveData<List<Kost>>()
    //publiv live data
    val kostList: LiveData<List<Kost>> = _kostList
    //filter
    private val _filters = MutableStateFlow(FilterKos())
    val filters: StateFlow<FilterKos> = _filters

    //selected kost
    private val _selectedKost = MutableLiveData<Kost>()
    val selectedKost: LiveData<Kost> = _selectedKost

    // LiveData untuk menyimpan state loading (menampilkan/menyembunyikan progress bar)
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // LiveData untuk menyimpan pesan error
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    /**
     * Fungsi untuk mengambil semua data kost dengan filter.
     * Dipanggil oleh UI.
     */
    fun fetchAllKosts() {
        val currentFilters = _filters.value
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val result = kostRepository.getAllKosts(currentFilters)
                //jika berhasil update LiveData
                _kostList.postValue(result)
            } catch (e: Exception) {
                _errorMessage.postValue(e.message)
            } finally {
                //sembunyikan loading indikator
                _isLoading.postValue(false)
            }
        }
    }

    /**
     * Fungsi untuk mengambil detail satu kost berdasarkan ID.
     * (Strukturnya akan mirip dengan fetchAllKosts)
     */
    fun fetchKostById(id: Long) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val result = kostRepository.getKostById(id)
                //jika berhasil update LiveData
                _selectedKost.postValue(result)
            } catch (e: Exception) {
                _errorMessage.postValue(e.message)
            } finally {
                //sembunyikan loading indikator
                _isLoading.postValue(false)
            }
        }
    }

    // Fungsi untuk jika menekan tombol filter
    fun applyFilters(newFilters: FilterKos) {
        _filters.value = newFilters
        fetchAllKosts()
    }
}
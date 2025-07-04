package com.example.kos_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kos_android.repository.KostRepository

class KostViewModelFactory(private val repository: KostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KostViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return KostViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
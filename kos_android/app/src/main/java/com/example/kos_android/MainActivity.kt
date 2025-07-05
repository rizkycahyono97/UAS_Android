package com.example.kos_android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.kos_android.data.network.ApiService
import com.example.kos_android.data.network.RetrofitClient
import com.example.kos_android.repository.KostRepositoryImpl
import com.example.kos_android.viewmodel.KostViewModel
import androidx.activity.viewModels
import com.example.kos_android.data.model.FilterKos
import com.example.kos_android.ui.list.KostListScreen
import com.example.kos_android.ui.theme.KosandroidTheme
import com.example.kos_android.viewmodel.KostViewModelFactory

class MainActivity : AppCompatActivity() {
    //buat semua dependency
    private val apiService: ApiService by lazy {
        RetrofitClient.instance
    }
    private val kostRepository: KostRepositoryImpl by lazy {
        KostRepositoryImpl(apiService)
    }
    private val kostViewModel: KostViewModel by viewModels {
        KostViewModelFactory(kostRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //memuat data pertama kali
        kostViewModel.fetchAllKosts(FilterKos())

        setContent {
            KosandroidTheme {
                KostListScreen(
                    viewModel = kostViewModel,
                    onNavigateToDetail = { kostId ->
                        // Logika untuk navigasi saat item diklik
                        // Untuk saat ini, kita hanya tampilkan Toast
                        Toast.makeText(
                            this,
                            "Navigasi detail untuk kost ID: $kostId",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }
    }
}
package com.example.kos_android

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kos_android.data.network.ApiService

class MainActivity : AppCompatActivity() {
    //buat semua dependency
    private val apiService: ApiService by lazy {
        retrofitClient.instance
    }
}
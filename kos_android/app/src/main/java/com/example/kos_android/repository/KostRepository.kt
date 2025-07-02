package com.example.kos_android.repository

import com.example.kos_android.data.model.FilterKos
import com.example.kos_android.data.model.Kost

interface KostRepository {
    /**
     * Mengambil daftar kost dari sumber data berdasarkan filter.
     * Mengembalikan List<Kost> jika sukses, atau melempar Exception jika gagal.
     */
    suspend fun getAllKosts(filters: FilterKos): List<Kost>

    /**
     * Mengambil satu detail kost berdasarkan ID.
     * Mengembalikan objek Kost jika sukses, atau melempar Exception jika gagal.
     */
    suspend fun getKostById(id: Long): Kost
}
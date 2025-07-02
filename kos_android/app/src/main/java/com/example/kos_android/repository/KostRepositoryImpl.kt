package com.example.kos_android.repository

import com.example.kos_android.data.model.FilterKos
import com.example.kos_android.data.model.Kost
import com.example.kos_android.data.network.ApiService

// Kelas ini menerima ApiService sebagai dependensi (Dependency Injection)
class KostRepositoryImpl(
    private val apiService: ApiService
) : KostRepository {
    override suspend fun getAllKosts(filters: FilterKos): List<Kost> {
        //panggil api service
        val response = apiService.getAllKosts(
            nama = filters.nama,
            tipe = filters.tipe,
            status = filters.statusKetersediaan,
            minHarga = filters.minHarga?.toInt(),
            maxHarga = filters.maxHarga?.toInt()
        )

        //jika panggilan api berhasil
        if (response.isSuccessful && response.body() != null) {
            //kembalikan data dari response
            return response.body()!!.data
        } else {
            throw Exception("Failed to fetch kosts ${response.message()}")
        }
    }

    override suspend fun getKostById(id: Long): Kost {
        //panggil api service
        val response = apiService.getKostById(id)

        //jika panggilan api service success
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!.data
        } else {
            if (response.code() == 404) {
                throw Exception("Kost with id $id not found")
            } else {
                throw Exception("Failed to fetch kost with id $id ${response.message()}")
            }
        }
    }
}
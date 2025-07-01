package com.example.kos_android.data.network

import com.example.kos_android.data.model.KostDetailResponse
import com.example.kos_android.data.model.KostListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    /**
     * Mengambil daftar semua kost dengan filter opsional.
     * Menggunakan anotasi @Query untuk setiap parameter filter.
     * Retrofit akan membuat URL seperti: /kosts?nama=...&tipe=...
     */
    @GET("kosts")
    suspend fun getAllKosts(
        @Query("nama") nama: String?,
        @Query("tipe") tipe: String?,
        @Query("status_ketersediaan") status: String?,
        @Query("min_harga") minHarga: Int?,
        @Query("max_harga") maxHarga: Int?
    ): Response<KostListResponse>

    /**
     * Mengambil detail satu kost berdasarkan ID-nya.
     * Menggunakan anotasi @Path untuk mengganti placeholder {id} di URL.
     */
    @GET("kosts/kos/{id}")
    suspend fun getKostById(
        @Path("id") kostId: Long
    ): Response<KostDetailResponse>
}
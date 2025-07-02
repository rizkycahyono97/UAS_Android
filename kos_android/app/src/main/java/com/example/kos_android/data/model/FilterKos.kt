package com.example.kos_android.data.model

/**
 * Data class ini berfungsi sebagai wadah untuk semua parameter filter
 * yang bisa dikirim ke endpoint GET /kosts.
 */
data class FilterKos(
    val nama: String? = null,
    val tipe: String? = null,
    val alamat: String? = null,
    val statusKetersediaan: String? = null,
    val minHarga: Int? = null,
    val maxHarga: Int? = null,
)
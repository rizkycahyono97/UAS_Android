package com.example.kos_android.data.model

import com.google.gson.annotations.SerializedName


/**
 * Data class untuk menampung respons dari endpoint yang mengembalikan daftar Kost.
 * Contoh: GET /api/v1/kosts
 */
data class KostListResponse(
    @SerializedName("code")
    val code: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: List<Kost>
)

/**
 * Data class untuk menampung respons dari endpoint yang mengembalikan satu detail Kost.
 * Contoh: GET /api/v1/kosts/kos/{id}
 */
data class KostDetailResponse(
    @SerializedName("code")
    val code: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: Kost
)

/**
 * Data class ini merepresentasikan satu objek Kost secara lengkap.
 */
data class Kost(
    @SerializedName("id")
    val id: Int,

    @SerializedName("user_id")
    val userId: Long,

    @SerializedName("nama")
    val nama: String,

    @SerializedName("deskripsi")
    val deskripsi: String,

    @SerializedName("tipe")
    val tipe: String,

    @SerializedName("alamat")
    val alamat: String,

    @SerializedName("harga")
    val harga: Long,

    @SerializedName("status_ketersediaan")
    val statusKetersediaan: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("deleted_at")
    val deletedAt: String,

    @SerializedName("fasilitas")
    val fasilitas: List<Fasilitas>,

    @SerializedName("kos_images")
    val kosImages: List<KosImages>
)

/**
 * Data class ini merepresentasikan satu objek Fasilitas.
 */
data class Fasilitas(
    @SerializedName("id")
    val id: Long,

    @SerializedName("kos_id")
    val kosId: Long,

    @SerializedName("nama_fasilitas")
    val namaFasilitas: String,

    @SerializedName("kos")
    val kos: Any? // Menggunakan Any? untuk menangani nilai null dengan aman
)

/**
 * Data class ini merepresentasikan satu objek KosImage.
 */
data class KosImages(
    @SerializedName("id")
    val id: Long,

    @SerializedName("kos_id")
    val kosId: Long,

    @SerializedName("url_image")
    val urlImage: String
)
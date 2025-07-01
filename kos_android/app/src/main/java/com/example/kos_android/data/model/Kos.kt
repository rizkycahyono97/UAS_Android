package com.example.kos_android.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class ini merepresentasikan satu objek FotoGaleri.
 * Anotasi @SerializedName digunakan untuk memetakan nama field di JSON ke properti di Kotlin.
 */

data class FotoGaleri(
    @SerializedName("ID")
    val id: Int,

    @SerializedName("KosID")
    val kosId: Int,

    
)
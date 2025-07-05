package com.example.kos_android.ui.list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.kos_android.data.model.FilterKos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    currentFilters: FilterKos,
    onApplyFilters: (FilterKos) -> Unit
) {
    // 1. Buat state lokal untuk menampung perubahan dari pengguna di dalam sheet ini.
    // Inisialisasi nilainya dengan filter yang sedang aktif saat ini.
    var nama by remember { mutableStateOf(currentFilters.nama ?: "") }
    var tipe by remember { mutableStateOf(currentFilters.tipe ?: "") }
    var status by remember { mutableStateOf(currentFilters.statusKetersediaan ?: "") }
    var minHarga by remember { mutableStateOf(currentFilters.minHarga?.toString() ?: "") }
    var maxHarga by remember { mutableStateOf(currentFilters.maxHarga?.toString() ?: "") }

    // Gunakan Column dengan scroll agar muat di layar kecil
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Filter Kost", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        // Filter Nama
        OutlinedTextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text("Nama Kos") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Filter Tipe
        Text("Tipe Kos", style = MaterialTheme.typography.labelLarge)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("putra", "putri", "campur").forEach { option ->
                FilterChip(
                    selected = (tipe == option),
                    onClick = { tipe = if (tipe == option) "" else option }, // Toggle on/off
                    label = { Text(option.replaceFirstChar { it.uppercase() }) }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Filter Status
        Text("Status Ketersediaan", style = MaterialTheme.typography.labelLarge)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("tersedia", "penuh").forEach { option ->
                FilterChip(
                    selected = (status == option),
                    onClick = { status = if (status == option) "" else option },
                    label = { Text(option.replaceFirstChar { it.uppercase() }) }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Filter Harga
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = minHarga,
                onValueChange = { minHarga = it },
                label = { Text("Harga Min") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = maxHarga,
                onValueChange = { maxHarga = it },
                label = { Text("Harga Max") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Tombol Aksi
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = {
                // Saat Reset diklik, buat objek filter kosong dan kirim
                onApplyFilters(FilterKos())
            }) {
                Text("Reset")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                // 2. Saat Terapkan diklik, buat objek FilterKos baru dari state lokal
                val newFilters = FilterKos(
                    nama = nama.ifBlank { null },
                    tipe = tipe.ifBlank { null },
                    statusKetersediaan = status.ifBlank { null },
                    minHarga = minHarga.toUIntOrNull()?.let { it },
                    maxHarga = maxHarga.toUIntOrNull()?.let { it }
                )
                // 3. Kirim filter baru tersebut melalui callback
                onApplyFilters(newFilters)
            }) {
                Text("Terapkan")
            }
        }
    }
}
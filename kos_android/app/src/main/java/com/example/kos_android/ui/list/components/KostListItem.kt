package com.example.kos_android.ui.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kos_android.data.model.Kost

@Composable
fun KostListItem(
    kost: Kost,
    onItemClick: (Kost) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onItemClick(kost) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.large // Sudut lebih melengkung
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Bagian Gambar
            AsyncImage(
                model = kost.kosImages.firstOrNull()?.urlImage ?: "", // Ambil gambar pertama atau kosong
                contentDescription = "Foto ${kost.nama}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop // Memastikan gambar memenuhi area tanpa distorsi
            )

            // Bagian Teks Konten
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Baris untuk Tipe dan Status (menggunakan Tag)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    KostTag(
                        text = kost.tipe.replaceFirstChar { it.uppercase() },
                        backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    KostTag(
                        text = kost.statusKetersediaan.replaceFirstChar { it.uppercase() },
                        backgroundColor = if (kost.statusKetersediaan.equals("tersedia", true)) Color(0xFFE8F5E9) else Color(0xFFFFEBEE),
                        contentColor = if (kost.statusKetersediaan.equals("tersedia", true)) Color(0xFF1B5E20) else Color(0xFFB71C1C)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Nama Kost
                Text(
                    text = kost.nama,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Alamat dengan Ikon
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Alamat",
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = kost.alamat.lines().firstOrNull() ?: "", // Ambil baris pertama alamat
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Harga
                Text(
                    text = "Rp ${kost.harga} / bulan",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

// Composable terpisah untuk membuat "Tag" atau "Chip"
@Composable
fun KostTag(text: String, backgroundColor: Color, contentColor: Color) {
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            color = contentColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}
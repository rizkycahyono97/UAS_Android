package com.example.kos_android.ui.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kos_android.data.model.Kost

@Composable
fun KostListItem(
    kost: Kost,
    onItemClick: (Kost) -> Unit //callback untuk klik
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onItemClick(kost) }, //panggil callback ketika kartu di klik
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            //load gambar
            AsyncImage(
                model = kost.kosImages.firstOrNull()?.urlImage,
                contentDescription = "Foto ${kost.nama}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            //layout untuk text dibawh gambar
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = kost.nama,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${kost.tipe.replaceFirstChar { it.uppercase() }} . ${kost.alamat.lines().first()}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
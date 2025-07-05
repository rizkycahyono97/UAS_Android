package com.example.kos_android.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kos_android.R // Pastikan Anda punya gambar di res/drawable
import com.example.kos_android.ui.list.components.KostListItem
import com.example.kos_android.viewmodel.KostViewModel

// (BARU) Composable untuk bagian header
@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Nge-Kost yuk!",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Temukan kos impianmu",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        // Ganti R.drawable.ic_house dengan gambar ilustrasi Anda sendiri
        Image(
            painter = painterResource(id = R.drawable.ic_house),
            contentDescription = "Ilustrasi Header",
            modifier = Modifier.size(64.dp)
        )
    }
}

// (BARU) Composable untuk baris pencarian dan filter
@Composable
fun SearchAndFilterBar(
    onFilterClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Cari kost paling nyaman di Ponorogo",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = onFilterClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "Filter"
                )
            }
        }
    }
}

@Composable
fun KostListScreen(
    viewModel: KostViewModel,
    onNavigateToDetail: (Long) -> Unit
) {
    val kostList by viewModel.kostList.observeAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val errorMessage by viewModel.errorMessage.observeAsState(initial = null)

    Scaffold { paddingValues ->
        // Gunakan Column untuk menata Header, Search Bar, dan Daftar secara vertikal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Tampilkan Header di bagian atas
            HeaderSection()

            // Tampilkan Search Bar di bawahnya
            SearchAndFilterBar(onFilterClick = { /* Logika filter nanti di sini */ })

            Spacer(modifier = Modifier.height(16.dp))

            // Box untuk menampilkan daftar atau state loading/error
            Box(
                modifier = Modifier.weight(1f), // weight(1f) agar Box ini mengisi sisa ruang
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(kostList) { kost ->
                        KostListItem(
                            kost = kost,
                            onItemClick = { onNavigateToDetail(kost.id.toLong()) }
                        )
                    }
                }

                if (isLoading) {
                    CircularProgressIndicator()
                }

                errorMessage?.let { message ->
                    Text(text = message, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}
package com.example.kos_android.ui.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kos_android.viewmodel.KostViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun KostDetailScreen(
    kostId: Long,
    viewModel: KostViewModel,
    onNavigateBack: () -> Unit
) {
    // Ambil data dari ViewModel saat Composable pertama kali dibuat
    LaunchedEffect(key1 = kostId) {
        viewModel.fetchKostById(kostId)
    }

    // Amati state dari ViewModel
    val kost by viewModel.selectedKost.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Kost") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else if (kost != null) {
                // Gunakan LazyColumn agar seluruh layar bisa di-scroll
                LazyColumn(modifier = Modifier.fillMaxSize()) {

                    // 1. Galeri Gambar (Horizontal Pager)
                    item {
                        val pagerState = rememberPagerState { kost!!.kosImages.size }
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                        ) { page ->
                            AsyncImage(
                                model = kost!!.kosImages[page].urlImage,
                                contentDescription = "Gambar Kos ${page + 1}",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    // 2. Konten Detail
                    item {
                        Column(modifier = Modifier.padding(16.dp)) {
                            // Nama & Harga
                            Text(kost!!.nama, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                "Rp ${kost!!.harga} / bulan",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            // Deskripsi
                            DetailSection(title = "Deskripsi", content = kost!!.deskripsi)

                            // Alamat
                            DetailSectionWithIcon(title = "Alamat", content = kost!!.alamat, icon = Icons.Default.LocationOn)

                            // Fasilitas
                            DetailSectionWithIcon(title = "Fasilitas", content = kost!!.fasilitas.toString(), icon = Icons.Default.Home)

                            // Info Pemilik
                            Divider(modifier = Modifier.padding(vertical = 16.dp))
                            Text("Informasi Pemilik", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Text("Nama: ${kost!!.username}")
                            Text("Telepon: ${kost!!.nomorTelepon}")

                            Spacer(modifier = Modifier.height(24.dp))

                            // Tombol Aksi
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(onClick = { /* TODO: Panggil Intent Telepon */ }, modifier = Modifier.weight(1f)) {
                                    Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(ButtonDefaults.IconSize))
                                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                    Text("Telepon")
                                }
                                OutlinedButton(onClick = { /* TODO: Panggil Intent WhatsApp */ }, modifier = Modifier.weight(1f)) {
                                    Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(ButtonDefaults.IconSize))
                                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                    Text("WhatsApp")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


// Composable bantuan untuk membuat bagian detail lebih rapi
@Composable
fun DetailSection(title: String, content: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(content, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun DetailSectionWithIcon(title: String, content: String, icon: ImageVector) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp), tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(8.dp))
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(content, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 28.dp))
    }
}
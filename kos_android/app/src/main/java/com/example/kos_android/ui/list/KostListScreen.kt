package com.example.kos_android.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import com.example.kos_android.viewmodel.KostViewModel
import androidx.compose.ui.Modifier
import com.example.kos_android.ui.list.components.KostListItem

@Composable
fun KostListScreen(
    viewModel: KostViewModel,
    onNavigationDetail: (Long) -> Unit
) {
    // Mengamati (observe) LiveData dari ViewModel dan mengubahnya menjadi State
    // Composable akan otomatis recompose (update) jika nilai LiveData ini berubah
    val kostList by viewModel.kostList.observeAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val errorMessage by viewModel.errorMessage.observeAsState(initial = null)

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            // Tampilkan daftar menggunakan LazyColumn (pengganti RecyclerView)
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(kostList) { kost ->
                    KostListItem(
                        kost = kost,
                        onItemClick = {
                            onNavigationDetail(kost.id.toLong())
                        }
                    )
                }
            }

            //progress bar
            if (isLoading) {
                CircularProgressIndicator()
            }

            errorMessage?.let { message ->
                Text(text = message)
            }
        }
    }

}


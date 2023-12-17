package com.polstat.polistis.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polstat.polistis.model.LaporanResponse
import com.polstat.polistis.ui.theme.PolistisTheme
import com.polstat.polistis.ui.theme.Purple
import com.polstat.polistis.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RiwayatScreen(
    token: String = "",
    riwayatViewModel: RiwayatViewModel = viewModel(factory = RiwayatViewModel.Factory)
) {
    val riwayats = dummyPengajuan()

    val scope = rememberCoroutineScope()
    val laporans by riwayatViewModel.laporansList.collectAsState()
    riwayatViewModel.setToken(token)
    riwayatViewModel.getDaftarPengajuanPasien()

    Column(
        modifier = Modifier
            .background(White)
            .fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Purple)
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Riwayat Pemeriksaan Anda",
                style= MaterialTheme.typography.titleLarge, color = White,
                modifier = Modifier.padding(25.dp)
            )
        }
        LaporanItem(laporans = laporans)
    }
}

@Composable
fun LaporanItem(
    laporans: List<LaporanResponse>?
) {
    if (laporans != null) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()) {
            items(items = laporans) {
                    laporan ->
                Card(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                        Column(
                            modifier = Modifier
                                .padding(top = 5.dp, bottom = 5.dp, start = 15.dp)
                                .height(150.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row (verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    tint = Purple,
                                    modifier = Modifier.size(24.dp))
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = laporan.namaDokter,
                                    color = Purple,
                                    style = MaterialTheme.typography.titleMedium,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Create,
                                    contentDescription = null,
                                    tint = Purple,
                                    modifier = Modifier.size(24.dp))
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = laporan.keluhan,
                                    color = Purple,
                                    style = MaterialTheme.typography.bodySmall,
                                    overflow = TextOverflow.Ellipsis
                                )

                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    tint = Purple,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = laporan.catatanDokter,
                                    color = Purple,
                                    style = MaterialTheme.typography.bodySmall,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                }
            }
        }


    } else {
        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Tidak ada daftar pengajuan", style = MaterialTheme.typography.titleMedium)
        }

    }
}


@Preview(showBackground = true)
@Composable
fun RiwayatScreenPreview() {
    PolistisTheme {
        RiwayatScreen()
    }
}
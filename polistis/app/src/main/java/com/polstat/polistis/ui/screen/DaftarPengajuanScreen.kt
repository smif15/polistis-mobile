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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.polstat.polistis.model.PengajuanResponse
import com.polstat.polistis.ui.theme.Green
import com.polstat.polistis.ui.theme.PolistisTheme
import com.polstat.polistis.ui.theme.Purple
import com.polstat.polistis.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaftarPengajuanScreen(
    token: String = "",
    daftarPengajuanViewModel: DaftarPengajuanViewModel = viewModel(factory = DaftarPengajuanViewModel.Factory),
    navController: NavController = rememberNavController()
) {
    val pengajuans = dummyPengajuan()

    val scope = rememberCoroutineScope()

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
            Text(text = "Daftar Pengajuan Pasien",
                style= MaterialTheme.typography.titleLarge, color = White,
                modifier = Modifier.padding(25.dp)
            )
        }
        val pengajuans by daftarPengajuanViewModel.pengajuansList.collectAsState()
        daftarPengajuanViewModel.setToken(token)
        daftarPengajuanViewModel.getAllPengajuan()
        PengajuanItem(pengajuans = pengajuans,
            onClickSetuju = { selectedId ->
                daftarPengajuanViewModel.selectedId = selectedId
                scope.launch {
                    daftarPengajuanViewModel.setuju()
                    daftarPengajuanViewModel.getAllPengajuan()
                }
            },
            onClickTolak = { selectedId ->
                daftarPengajuanViewModel.selectedId = selectedId
                scope.launch {
                    daftarPengajuanViewModel.tolak()
                    daftarPengajuanViewModel.getAllPengajuan()
                }
            }
            )
        }
}


@Composable
fun PengajuanItem(
    pengajuans: List<PengajuanResponse>?,
    onClickSetuju: (Long) -> Unit =  { },
    onClickTolak: (Long) -> Unit = { }
) {
    if (pengajuans != null) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()) {
            items(items = pengajuans) {
                    pengajuan ->
                Card(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(top = 5.dp, bottom = 5.dp, start = 15.dp)
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row (verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    tint = Purple,
                                    modifier = Modifier.size(24.dp))
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = pengajuan.namaPasien,
                                    color = Purple,
                                    style = MaterialTheme.typography.titleMedium,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.DateRange,
                                    contentDescription = null,
                                    tint = Purple,
                                    modifier = Modifier.size(24.dp))
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = pengajuan.waktu,
                                    color = Purple,
                                    style = MaterialTheme.typography.bodySmall,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        Column(
                            modifier = Modifier.width(120.dp)
                                .fillMaxHeight()
                                .padding(0.dp,0.dp,15.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
                                    pengajuan.id?.let { onClickSetuju(it) }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(33.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Green,
                                )
                            ) {
                                Text("Setujui", style = MaterialTheme.typography.labelMedium,
                                    color = White)
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Button(
                                onClick = {
                                    pengajuan.id?.let { onClickTolak(it) }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(30.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Red,
                                )
                            ) {
                                Text("Tolak", style = MaterialTheme.typography.labelMedium,
                                    color = White)
                            }
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


//data dummy
data class Pengajuan(val namaPasien: String, val waktu: String)

fun dummyPengajuan(): List<Pengajuan>{
    val dummies = mutableListOf<Pengajuan>()

    for (i in 1..10) {
        dummies.add(Pengajuan("Faris Iqbal", "$i-12-2023, 09.00-10.00"))
    }

    return dummies
}

//preview
@Preview
@Composable
fun DaftarPengajuanPreview() {
    PolistisTheme {
        DaftarPengajuanScreen()
    }
}
package com.polstat.polistis.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polstat.polistis.ui.theme.Purple
import com.polstat.polistis.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaporanScr(
    token : String = "",
    id : Long = 0,
    laporanVM: LaporanVM = viewModel(factory = LaporanVM.Factory),
    backToHome: () -> Unit = { }
) {
    val scope = rememberCoroutineScope()
    laporanVM.setToken(token)
    laporanVM.setPengajuanId(id)
    laporanVM.ambil()

    val pengajuan by laporanVM.pengajuanData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = White,
            )
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)
            ) {
                Text(
                    text = "Formulir Rekam Medis",
                    style = MaterialTheme.typography.titleLarge,
                    color = Purple,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(text = "Nama Pasien",
                    style= MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start, color = Purple
                )
                Text(text = "${pengajuan?.namaPasien}",
                    style = MaterialTheme.typography.titleMedium, color = Purple,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 5.dp, 5.dp, 10.dp)
                )


                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Keluhan",
                    style= MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start, color = Purple
                )
                Text(text = "${pengajuan?.keluhan}",
                    style = MaterialTheme.typography.titleMedium, color = Purple,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 5.dp, 5.dp, 10.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Catatan Dokter",
                    style= MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start, color = Purple
                )
                TextField(
                    value = laporanVM.catatanDokter,
                    onValueChange = { laporanVM.updateCatatanDokter(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    )
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {
                        scope.launch {
                            when(laporanVM.buatLaporan()) {
                                BuatLaporanResult.Success -> backToHome()
                                else -> println("Error saat submit")
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Purple,
                    )
                ) {
                    Text("Submit", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }

}
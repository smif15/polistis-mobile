package com.polstat.polistis.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polstat.polistis.ui.theme.PolistisTheme
import com.polstat.polistis.ui.theme.Purple
import com.polstat.polistis.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    toFormDaftar: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(White)
            .fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp) //belum tahu gimana biar relatif (1/3 screen misal)
            .background(Purple)
        ) {
            Text(text = "Selamat datang di Layanan Polistis!",
                style=MaterialTheme.typography.titleLarge, color = White,
                modifier = Modifier.padding(25.dp)
            )
        }

        Card(modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth()
            .height(150.dp)
        ) {
            Column(modifier = Modifier.padding(all = 10.dp)) {
                Text("Jam Buka Poliklinik", style=MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(10.dp)
                )
                Text("Senin-Jumat : 08.00-15.00", style=MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(15.dp,10.dp,0.dp,5.dp)
                )
                Text("Sabtu-Minggu : Libur", style=MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(15.dp,10.dp,0.dp,5.dp)
                )
            }
        }

        Card(modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxWidth()
            .height(300.dp)
        ) {
            Column(modifier = Modifier.padding(all = 10.dp)) {
                Text("Pendaftaran Temu Janji", style=MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Column(modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Silakan mendaftar! Anda hanya dapat mendaftar satu kali sebelum diproses", style=MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(10.dp),
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = {
                        toFormDaftar()
                    },
                    modifier = Modifier
                        .width(125.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Purple,
                    )
                ) {
                    Text("Daftar", style = MaterialTheme.typography.titleMedium)
                }

            }

        }

    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PolistisTheme {
        HomeScreen()
    }
}

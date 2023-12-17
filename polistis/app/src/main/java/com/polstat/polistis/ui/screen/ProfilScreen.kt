package com.polstat.polistis.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polstat.polistis.ui.theme.Green
import com.polstat.polistis.ui.theme.PolistisTheme
import com.polstat.polistis.ui.theme.Purple
import com.polstat.polistis.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilScreen(
    token : String = "",
    profilViewModel: ProfilViewModel = viewModel(factory = ProfilViewModel.Factory),
    onClickEditProfil: () -> Unit = {},
    onClickLogout: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    profilViewModel.getInfoPasien(token)
    val pasien by profilViewModel.pasienData.collectAsState()
    Column(
        modifier = Modifier
            .background(White)
            .fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(Purple)
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Profil Anda",
                style= MaterialTheme.typography.titleLarge, color = White,
                modifier = Modifier.padding(25.dp)

            )
            Spacer(modifier = Modifier.height(10.dp))
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ){
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profil",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(75.dp),
                    tint = White
                )

                //nama
                Text(text = "Nama Anda",
                    style = MaterialTheme.typography.titleMedium, color = White,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp, 5.dp, 5.dp, 5.dp)
                )
                Text(text = "${pasien?.nama}",
                    style = MaterialTheme.typography.titleMedium, color = White,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp, 0.dp, 5.dp, 10.dp)
                )
                //alergi
                Text(text = "Alergi",
                    style = MaterialTheme.typography.titleMedium, color = White,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp, 5.dp, 5.dp, 5.dp)
                )
                Text(text = "${pasien?.alergi}",
                    style = MaterialTheme.typography.titleMedium, color = White,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp, 0.dp, 5.dp, 10.dp)
                )
                //riwayatPenyakit
                Text(text = "Riwayat Penyakit",
                    style = MaterialTheme.typography.titleMedium, color = White,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp, 5.dp, 5.dp, 5.dp)
                )
                Text(text = "${pasien?.riwayatPenyakit}",
                    style = MaterialTheme.typography.titleMedium, color = White,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp, 0.dp, 5.dp, 10.dp)
                )
            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(White)
            .padding(5.dp)
        ) {
            Spacer(modifier = Modifier.height(5.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .background(color = Green, shape = RoundedCornerShape(8.dp))
                    .padding(15.dp)
                    .clickable {
                        onClickEditProfil()
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = "Edit Profil",
                    style = MaterialTheme.typography.titleMedium, color = White,
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .background(color = Color.Red, shape = RoundedCornerShape(8.dp))
                    .padding(15.dp)
                    .clickable{
                        //profilViewModel.logout()
                        onClickLogout()
                    },
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = "Log out",
                    style = MaterialTheme.typography.titleMedium, color = White,
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProfilScreenPreview() {
    PolistisTheme {
        ProfilScreen()
    }
}
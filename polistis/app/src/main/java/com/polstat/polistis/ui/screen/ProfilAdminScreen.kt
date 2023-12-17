package com.polstat.polistis.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polstat.polistis.ui.theme.Purple
import com.polstat.polistis.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilAdminScreen(
    token : String = "",
    profilAdminViewModel: ProfilAdminViewModel = viewModel(factory = ProfilAdminViewModel.Factory),
    onClickLogout: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .background(White)
            .fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
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

                Text(text = "Nama Anda",
                    style = MaterialTheme.typography.titleMedium, color = White,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp, 5.dp, 5.dp, 5.dp)
                )
                Text(text = "Admin",
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
            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .background(color = Color.Red, shape = RoundedCornerShape(8.dp))
                    .padding(15.dp)
                    .clickable{
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
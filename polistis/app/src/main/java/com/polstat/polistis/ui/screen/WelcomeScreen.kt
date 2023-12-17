package com.polstat.polistis.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polstat.polistis.ui.theme.PolistisTheme
import com.polstat.polistis.ui.theme.Purple
import com.polstat.polistis.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    Column (
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp)
                ) {
                    Text(
                        text = "Selamat datang di Polistis!",
                        style = MaterialTheme.typography.titleLarge,
                        color = Purple
                    )

                    Spacer(modifier = Modifier.height(250.dp))

                    Button(
                        onClick = { onLoginClick() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Purple,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Masuk", style = MaterialTheme.typography.titleMedium)
                    }
                    Button(
                        onClick = { onRegisterClick() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Purple,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Daftar", style = MaterialTheme.typography.titleMedium)
                    }

                }
        }
    }
}
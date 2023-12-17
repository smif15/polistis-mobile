package com.polstat.polistis.ui.screen

import android.widget.Toast
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polstat.polistis.ui.theme.PolistisTheme
import com.polstat.polistis.ui.theme.Purple
import com.polstat.polistis.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormProfilScreen(
    token : String = "",
    formProfilViewModel: FormProfilViewModel = viewModel(factory = FormProfilViewModel.Factory),
    backToProfil: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    formProfilViewModel.setToken(token)

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
                    text = "Profil",
                    style = MaterialTheme.typography.titleLarge,
                    color = Purple,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(text = "Nama",
                    style= MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start, color = Purple
                )
                TextField(
                    value = formProfilViewModel.nama,
                    onValueChange = { formProfilViewModel.updateNama(it) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Alergi",
                    style= MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start, color = Purple
                )
                TextField(
                    value = formProfilViewModel.alergi,
                    onValueChange = { formProfilViewModel.updateAlergi(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                )


                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Riwayat Penyakit",
                    style= MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start, color = Purple
                )
                TextField(
                    value = formProfilViewModel.riwayatPenyakit,
                    onValueChange = { formProfilViewModel.updateRiwayatPenyakit(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    )
                )

                Spacer(modifier = Modifier.height(30.dp))

                val context = LocalContext.current
                Button(
                    onClick = {
                        scope.launch {
                            when(formProfilViewModel.updateProfilPasien()) {
                                UpdateProfilResult.Success -> backToProfil()
                                else -> {
                                    Toast.makeText(context, "Edit profil gagal, field nama harus diisi!",
                                        Toast.LENGTH_SHORT).show()
                                }
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
                    Text("Edit", style = MaterialTheme.typography.titleMedium)
                }
            }
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun FormProfilScreenPreview() {
    PolistisTheme {
        FormProfilScreen()
    }
}
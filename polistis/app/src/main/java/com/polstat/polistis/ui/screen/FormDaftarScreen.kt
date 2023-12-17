package com.polstat.polistis.ui.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polstat.polistis.ui.theme.PolistisTheme
import com.polstat.polistis.ui.theme.Purple
import com.polstat.polistis.ui.theme.White
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormDaftarScreen(
    token : String = "",
    formDaftarViewModel: FormDaftarViewModel = viewModel(factory = FormDaftarViewModel.Factory),
    backToHome: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    formDaftarViewModel.setToken(token)

    var tanggal by remember { mutableStateOf("") }
    var waktu by remember { mutableStateOf("") }

    val context = LocalContext.current

    //dateee
    val calendar = Calendar.getInstance()

    var selectedDateText by remember {
        mutableStateOf("")
    }

    var selectedTimeText by remember {
        mutableStateOf("")
    }

    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = 0

    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            selectedDateText = "$selectedDayOfMonth-${selectedMonth + 1}-$selectedYear"
        }, year, month, dayOfMonth
    )

    val timePicker = TimePickerDialog(
        context,
        { _, selectedHour: Int, _: Int ->
            selectedTimeText = "$selectedHour:00"
        }, hour, minute, true
    )

    val minDate = Calendar.getInstance().apply {
        add(Calendar.DAY_OF_MONTH, 1)
    }.timeInMillis

    val maxDate = Calendar.getInstance().apply {
        add(Calendar.DAY_OF_MONTH, 7)
    }.timeInMillis

    datePicker.datePicker.minDate = minDate
    datePicker.datePicker.maxDate = maxDate

    fun getWaktu(): String {
        return "$selectedDateText ${hour-7}:00"
    }

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
                    text = "Formulir Pendaftaran",
                    style = MaterialTheme.typography.titleLarge,
                    color = Purple,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(text = "Tanggal Pertemuan",
                    style= MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start, color = Purple)

                //picker date
                Button(
                    onClick = {
                        datePicker.show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Purple,
                    )
                ) {
                    Text("Pilih tanggal", style = MaterialTheme.typography.titleMedium)
                }

                //isian nilai tanggal
                tanggal = if(selectedDateText.isNotEmpty()) {
                    "$selectedDateText"
                } else {
                    "Silakan isi tanggal"
                }

                TextField(
                    value = tanggal,
                    onValueChange = { tanggal = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    singleLine = true,
                    readOnly = true
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Waktu Pertemuan",
                    style= MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start, color = Purple)


                //picker time
                Button(
                    onClick = {
                        timePicker.show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Purple,
                    )
                ) {
                    Text("Pilih waktu", style = MaterialTheme.typography.titleMedium)
                }

                //isian nilai waktu
                waktu = if(selectedTimeText.isNotEmpty()) {
                    "$selectedTimeText"
                } else {
                    "Silakan isi waktu"
                }

                TextField(
                    value = waktu,
                    onValueChange = { waktu = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    singleLine = true,
                    readOnly = true
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Keluhan Anda",
                    style= MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start, color = Purple)
                TextField(
                    value = formDaftarViewModel.keluhan,
                    onValueChange = { formDaftarViewModel.updateKeluhan(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    )
                )


                Spacer(modifier = Modifier.height(30.dp))

                val cxt = LocalContext.current
                Button(
                    onClick = {
                        scope.launch {
                            formDaftarViewModel.updateWaktu(getWaktu())

                            when(formDaftarViewModel.buatPengajuan()) {
                                BuatProfilResult.Success -> {
                                    Toast.makeText(cxt, "Pendaftaran berhasil!",
                                        Toast.LENGTH_SHORT).show()
                                    backToHome()
                                }
                                else -> {
                                    Toast.makeText(cxt, "Pendaftaran gagal. Semua isian harus diisi",
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
                    Text("Daftar", style = MaterialTheme.typography.titleMedium)
                }
            }
        }

    }
}





@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun FormDaftarScreenPreview() {
    PolistisTheme {
        FormDaftarScreen()
    }
}
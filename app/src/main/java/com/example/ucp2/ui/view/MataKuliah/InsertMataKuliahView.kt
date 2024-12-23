package com.example.ucp2.ui.view.MataKuliah

import android.text.Editable.Factory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.dao.DosenDao
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.data.isiDDMataKuliah
import com.example.ucp2.ui.customwidget.CustomTopAppBar
import com.example.ucp2.ui.customwidget.DynamicSelectedField
import com.example.ucp2.ui.navigation.AlamatNavigasi
import com.example.ucp2.ui.viewmodel.matakuliahvm.FormErrorState
import com.example.ucp2.ui.viewmodel.matakuliahvm.MKUIState
import com.example.ucp2.ui.viewmodel.matakuliahvm.MataKuliahEvent
import com.example.ucp2.ui.viewmodel.matakuliahvm.MataKuliahViewModel
import com.example.ucp2.ui.viewmodel.matakuliahvm.PenyediaMKViewModel
import kotlinx.coroutines.launch

object DestinasiInsert : AlamatNavigasi {
    override val route: String = "insert_MK"
}

@Composable
fun InsertMKView(
    onBack:() -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MataKuliahViewModel = viewModel(factory = PenyediaMKViewModel.Factory)
){
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ){
            padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            CustomTopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Mata Kuliah"
            )

            InsertBodyMK(
                uiState = uiState,
                onValueChange = { updatedEvent ->
                    viewModel.updateState(updatedEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData()
                    }
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun InsertBodyMK(
    modifier: Modifier = Modifier,
    onValueChange: (MataKuliahEvent) -> Unit,
    uiState: MKUIState,
    onClick: () -> Unit
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormMataKuliah(
            MataKuliahEvent = uiState.MataKuliahEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Simpan")
        }
    }
}

@Composable
fun FormMataKuliah(
    MataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    onValueChange: (MataKuliahEvent) -> Unit,
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
) {
    val jenis = listOf("Pemrograman", "Database", "UI/UX", "Jaringan")
    var chosenDropdown by remember { mutableStateOf("")
    }

    Column (
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = MataKuliahEvent.nama,
            onValueChange = {
                onValueChange(MataKuliahEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama") },
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = MataKuliahEvent.kode, onValueChange = {
                onValueChange(MataKuliahEvent.copy(kode = it))
            },
            label = { Text("kode") },
            isError = errorState.kode != null,
            placeholder = { Text("Masukkan kode") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = errorState.kode ?: "",
            color = Color.Red)

        Spacer(modifier = Modifier.height(2.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = MataKuliahEvent.sks,
            onValueChange = {
                onValueChange(MataKuliahEvent.copy(sks = it))
            },
            label = { Text("sks") },
            isError = errorState.sks != null,
            placeholder = { Text("Masukkan sks") },
        )
        Text(
            text = errorState.sks ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(2.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = MataKuliahEvent.semester,
            onValueChange = {
                onValueChange(MataKuliahEvent.copy(semester = it))
            },
            label = { Text("semester") },
            isError = errorState.semester != null,
            placeholder = { Text("Masukkan semester") },
        )
        Text(
            text = errorState.semester ?: "",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(2.dp))
        Text(text = "Jenis Matkul")
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            // Baris pertama
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                jenis.take(2).forEach { jns ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.weight(1f)
                    ) {
                        RadioButton(
                            selected = MataKuliahEvent.jenis == jns,
                            onClick = {
                                onValueChange(MataKuliahEvent.copy(jenis = jns))
                            },
                        )
                        Text(
                            text = jns,
                        )
                    }
                }
            }

            // Baris kedua
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                jenis.drop(2).forEach { jns ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.weight(1f)
                    ) {
                        RadioButton(
                            selected = MataKuliahEvent.jenis == jns,
                            onClick = {
                                onValueChange(MataKuliahEvent.copy(jenis = jns))
                            },
                        )
                        Text(
                            text = jns,
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text("Silahkan pilih Mata Kuliah yang anda inginkan",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light)
        Spacer(modifier = Modifier.padding(8.dp))
        DynamicSelectedField(
            selectedValue = chosenDropdown,
            options = isiDDMataKuliah.options,
            label = "Mata Kuliah",
            onValueChangedEvent = {
                chosenDropdown = it
            }
        )

    }
}
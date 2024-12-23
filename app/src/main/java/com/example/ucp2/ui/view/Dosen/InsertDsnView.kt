package com.example.ucp2.ui.view.Dosen

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.customwidget.CustomTopAppBar
import com.example.ucp2.ui.viewmodel.dosenvm.DosenEvent
import com.example.ucp2.ui.viewmodel.dosenvm.DosenViewModel
import com.example.ucp2.ui.viewmodel.dosenvm.DsnUIState
import com.example.ucp2.ui.viewmodel.dosenvm.FormErrorState
import com.example.ucp2.ui.viewmodel.dosenvm.PenyediaDsnViewModel
import kotlinx.coroutines.launch



@Composable
fun InsertDsnView(
    onBack:() -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DosenViewModel = viewModel(factory = PenyediaDsnViewModel.Factory)
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
                judul = "Tambah Dosen"
            )

            InsertBodyDsn(
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
fun InsertBodyDsn(
    modifier: Modifier = Modifier,
    onValueChange: (DosenEvent) -> Unit,
    uiState: DsnUIState,
    onClick: () -> Unit
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormDosen(
            DosenEvent = uiState.DosenEvent,
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
fun FormDosen(
    DosenEvent: DosenEvent = DosenEvent(),
    onValueChange: (DosenEvent) -> Unit,
    errorState: FormErrorState = FormErrorState(),
    modifier: Modifier = Modifier
) {
    val jenisKelamin = listOf("Laki-laki", "Perempuan")

    Column (
        modifier = modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = DosenEvent.nama,
            onValueChange = {
                onValueChange(DosenEvent.copy(nama = it))
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
            value = DosenEvent.nidn, onValueChange = {
                onValueChange(DosenEvent.copy(nidn = it))
            },
            label = { Text("NIDN") },
            isError = errorState.nidn != null,
            placeholder = { Text("Masukkan nidn") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(text = errorState.nidn ?: "",
            color = Color.Red)

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Jenis Kelamin")
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            jenisKelamin.forEach { jk ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = DosenEvent.jenisKelamin == jk,
                        onClick = {
                            onValueChange(DosenEvent.copy(jenisKelamin = jk))
                        },
                    )
                    Text(
                        text = jk,
                    )
                }
            }
        }
    }
}
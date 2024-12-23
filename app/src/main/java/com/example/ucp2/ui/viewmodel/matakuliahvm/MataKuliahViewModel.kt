package com.example.ucp2.ui.viewmodel.matakuliahvm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMK
import kotlinx.coroutines.launch

//data class variabel yang menyimpan data input form
data class MataKuliahEvent(
    val kode: String = "",
    val nama: String = "",
    val sks: String = "",
    val semester: String = "",
    val jenis: String = "",
    val dosenPengampu: String = "",
)

//Menyimpan input form ke dalam entity
fun MataKuliahEvent.toMataKuliahEntity(): MataKuliah = MataKuliah(
    kode = kode,
    nama = nama,
    sks = sks,
    semester = semester,
    jenis = jenis,
    dosenPengampu = dosenPengampu,
)

data class MKUIState(
    val MataKuliahEvent: MataKuliahEvent = MataKuliahEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null
)

data class FormErrorState(
    val kode: String? = null,
    val nama: String? = null,
    val sks: String? = null,
    val semester: String? = null,
    val jenis: String? = null,
    val dosenPengampu: String? = null,
){
    fun isValid(): Boolean {
        return kode == null && nama == null && sks == null && semester == null &&
                jenis == null && dosenPengampu == null
    }
}

class MataKuliahViewModel(private val  repositoryMK: RepositoryMK) : ViewModel() {
    var uiState by mutableStateOf(MKUIState())

    fun updateState(MataKuliahEvent: MataKuliahEvent) {
        uiState = uiState.copy(
            MataKuliahEvent = MataKuliahEvent,

            )
    }

    private fun validateField(): Boolean {
        val event = uiState.MataKuliahEvent
        val errorState = FormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "sks tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "semester tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "jenis tidak boleh kosong",
            dosenPengampu = if (event.jenis.isNotEmpty()) null else "dosen pengampu tidak boleh kosong"
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = uiState.MataKuliahEvent
        if (validateField()) {
            viewModelScope.launch {
                try {
                    repositoryMK.insertMK(currentEvent.toMataKuliahEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil Disimpan",
                        MataKuliahEvent = MataKuliahEvent(), //reset input form
                        isEntryValid = FormErrorState() //reset error state
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data Gagal Disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid, Periksa kembali data anda"
            )
        }
    }

    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }
}





package com.example.ucp2.ui.viewmodel.matakuliahvm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.MataKuliah
import com.example.ucp2.repository.RepositoryMK
import com.example.ucp2.ui.navigation.DestinasiUpdate
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.Normalizer.Form

class UpdateMataKuliahViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMK: RepositoryMK
): ViewModel() {
    var updateUIState by mutableStateOf(MKUIState())
        private set

    private val _kode: String = checkNotNull(savedStateHandle[DestinasiUpdate.KODE])

    init {
        viewModelScope.launch {
            updateUIState = repositoryMK.getMK(_kode)
                .filterNotNull()
                .first()
                .toUIStateMK()
        }
    }

    fun updateState(MataKuliahEvent: MataKuliahEvent) {
        updateUIState = updateUIState.copy(
            MataKuliahEvent = MataKuliahEvent,
        )
    }

    fun validateFields(): Boolean {
        val event = updateUIState.MataKuliahEvent
        val errorState = FormErrorState(
            kode = if (event.kode.isNotEmpty()) null else "kode tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            sks = if (event.sks.isNotEmpty()) null else "sks tidak boleh kosong",
            jenis = if (event.jenis.isNotEmpty()) null else "jenis tidak boleh kosong",
            semester = if (event.semester.isNotEmpty()) null else "semester tidak boleh kosong",
            dosenPengampu = if (event.dosenPengampu.isNotEmpty()) null else "dosenPengampu tidak boleh kosong"
        )

        updateUIState = updateUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData() {
        val currentEvent = updateUIState.MataKuliahEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMK.updateMK(currentEvent.toMataKuliahEntity())
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        MataKuliahEvent = MataKuliahEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("snackbar message diatur: ${updateUIState.
                    snackBarMessage}")
                } catch (e: Exception) {
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else {
            updateUIState = updateUIState.copy(
                snackBarMessage = "Data gagal diupdate"
            )
        }
    }

    fun resetSnackBarMessage() {
        updateUIState = updateUIState.copy(snackBarMessage = null)
    }
}

fun MataKuliah.toUIStateMK(): MKUIState = MKUIState(
    MataKuliahEvent = this.toDetailUiEvent(),
)
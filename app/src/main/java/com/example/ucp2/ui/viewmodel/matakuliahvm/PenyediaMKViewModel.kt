package com.example.ucp2.ui.viewmodel.matakuliahvm

import com.example.ucp2.KrsApp
import android.text.Editable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.data.entity.MataKuliah

object PenyediaMKViewModel{
    val Factory = viewModelFactory {
        initializer {
            MataKuliahViewModel(
                krsApp().containerApp.repositoryMK,
                krsApp().containerApp.repositoryDosen
            )
        }
        initializer {
            HomeMKViewModel(
                krsApp().containerApp.repositoryMK
            )
        }
        initializer {
            DetailMataKuliahViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repositoryMK
            )
        }
        initializer {
            UpdateMataKuliahViewModel(
                createSavedStateHandle(),
                krsApp().containerApp.repositoryMK,
                krsApp().containerApp.repositoryDosen
            )
        }
    }
}

fun CreationExtras.krsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)
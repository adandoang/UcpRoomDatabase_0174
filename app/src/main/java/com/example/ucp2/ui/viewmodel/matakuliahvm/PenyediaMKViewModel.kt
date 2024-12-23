package com.example.ucp2.ui.viewmodel.matakuliahvm

import KrsApp
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
                krsApp().containerApp.repositoryMK
            )
        }
        initializer {
            HomeMKViewModel(
                krsApp().containerApp.repositoryMK
            )
        }
        initializer {
            DetailMataKuliahViewModel(
                krsApp().containerApp.repositoryMK
            )
        }
        initializer {
            UpdateMataKuliahViewModel(
                krsApp().containerApp.repositoryMK
            )
        }
    }
}

fun CreationExtras.krsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)
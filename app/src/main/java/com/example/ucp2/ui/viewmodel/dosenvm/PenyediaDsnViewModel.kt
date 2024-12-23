package com.example.ucp2.ui.viewmodel.dosenvm

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2.KrsApp

object PenyediaDsnViewModel{
    val Factory = viewModelFactory {
        initializer {
            DosenViewModel(
                krsApp().containerApp.repositoryDosen
            )
        }
        initializer {
            HomeDsnViewModel(
                krsApp().containerApp.repositoryDosen
            )
        }
    }
}

fun CreationExtras.krsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)
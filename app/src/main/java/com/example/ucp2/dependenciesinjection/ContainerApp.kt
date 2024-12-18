package com.example.ucp2.dependenciesinjection

import android.content.Context
import com.example.ucp2.data.database.KrsDatabase
import com.example.ucp2.repository.LocalRepositoryDosen
import com.example.ucp2.repository.LocalRepositoryMK
import com.example.ucp2.repository.RepositoryDosen
import com.example.ucp2.repository.RepositoryMK

interface InterfaceContainerApp {
    val repositoryMK: RepositoryMK
    val repositoryDosen: RepositoryDosen
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryDosen: RepositoryDosen by lazy {
        LocalRepositoryDosen(KrsDatabase.getDatabase(context).dosenDao())
    }
    override val repositoryMK: RepositoryMK by lazy {
        LocalRepositoryMK(KrsDatabase.getDatabase(context).mataKuliahDao())
    }
}
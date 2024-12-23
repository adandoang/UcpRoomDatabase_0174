package com.example.ucp2

import android.app.Application
import com.example.ucp2.dependenciesinjection.ContainerApp
import com.example.ucp2.dependenciesinjection.InterfaceContainerApp

class KrsApp : Application() {
    lateinit var containerApp: ContainerApp //fungsinya untuk menyimpan

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this) //membuat instance
        //instance adalah object yang dibuat dari class
    }
}
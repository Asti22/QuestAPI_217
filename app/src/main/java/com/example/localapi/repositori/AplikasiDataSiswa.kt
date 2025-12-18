package com.example.localapi.repositori


import android.app.Application

class AplikasiDataSiswa : Application() {

    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = DefaultContainerApp()
    }
}

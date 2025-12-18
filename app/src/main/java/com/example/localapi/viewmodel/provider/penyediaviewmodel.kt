package com.example.localapi.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.localapi.repositori.DefaultContainerApp


// Ambil Application (AplikasiDataSiswa) dari CreationExtras
// Ambil Application dari CreationExtras
fun CreationExtras.aplikasiDataSiswa(): DefaultContainerApp.AplikasiDataSiswa =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
            as DefaultContainerApp.AplikasiDataSiswa


// Factory untuk menyediakan ViewModel
// Factory untuk menyediakan ViewModel
object PenyediaViewModel {

    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(
                aplikasiDataSiswa().container.repositoryDataSiswa
            )
        }

        initializer {
            EntryViewModel(
                aplikasiDataSiswa().container.repositoryDataSiswa
            )
        }
    }
}


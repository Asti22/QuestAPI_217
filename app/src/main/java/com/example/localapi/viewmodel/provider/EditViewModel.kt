package com.example.localapi.viewmodel.provider

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localapi.modeldata.DetailSiswa
import com.example.localapi.modeldata.UIStateSiswa
import com.example.localapi.modeldata.toDataSiswa
import com.example.localapi.modeldata.toUiStateSiswa
import com.example.localapi.repositori.RepositoryDataSiswa
import kotlinx.coroutines.launch
import retrofit2.Response

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Int =
        checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])

    init {
        viewModelScope.launch {
            uiStateSiswa = repositoryDataSiswa
                .getSatuSiswa(idSiswa)
                .toUiStateSiswa(isEntryValid = true)
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = uiStateSiswa.copy(
            detailSiswa = detailSiswa,
            isEntryValid = validasiInput(detailSiswa)
        )
    }

    private fun validasiInput(detailSiswa: DetailSiswa): Boolean {
        return with(detailSiswa) {
            nama.isNotBlank() &&
                    alamat.isNotBlank() &&
                    telpon.isNotBlank()
        }
    }

    fun editSatuSiswa() {
        if (!validasiInput(uiStateSiswa.detailSiswa)) return

        viewModelScope.launch {
            val response: Response<Void> =
                repositoryDataSiswa.editSatuSiswa(
                    idSiswa,
                    uiStateSiswa.detailSiswa.toDataSiswa()
                )

            if (response.isSuccessful) {
                println("Update Sukses")
            } else {
                println("Update Gagal: ${response.message()}")
            }
        }
    }
}

package com.example.localapi.modeldata

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

interface ContainerApp{
    val repositoryDataSiswa : RepositoryDataSiswa
}
class DefaultContainerApp : ContainerApp{
    private val baseurl = "http://10.0.2.2/umyTI"

    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val klien = OkHttpClient.Builder()
        .addInterceptor (logging)
        .build()
}
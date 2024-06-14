package com.tecsup.prototipo_proyecto.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private val URL_BASE = "https://jellyfish-app-olbh8.ondigitalocean.app/api/"

    val retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}
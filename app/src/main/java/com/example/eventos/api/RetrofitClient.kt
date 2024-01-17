package com.example.eventos.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Field

// Configuração do Retrofit
object RetrofitInstance {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://api.observador.pt/wp/lists/featured/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }



}

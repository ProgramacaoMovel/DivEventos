package com.example.eventos.api

import com.example.eventos.model.LoginRequest
import com.example.eventos.model.LoginResponse
import com.example.eventos.model.Noticia
import com.example.eventos.model.ResponseModel
import com.example.eventos.model.UserRegistration
import com.example.eventos.model.UserRegistrationResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body


// Definição da interface ApiService
interface ApiService {
    @FormUrlEncoded
        @POST("login")
        fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>



        @POST("register")
        fun registerUser(@Body newUser: UserRegistration): Call<UserRegistrationResponse>


        @POST("noticias")
        fun criarNoticia(@Body noticia: Noticia): Call<ResponseModel>



}

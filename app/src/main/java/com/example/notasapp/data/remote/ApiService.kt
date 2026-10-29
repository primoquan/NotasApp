package com.example.notasapp.data.remote

import com.example.notasapp.data.model.request.LoginRequest
import com.example.notasapp.data.model.request.NotaRequest
import com.example.notasapp.data.model.response.CrearNotaResponse
import com.example.notasapp.data.model.response.LoginResponse
import com.example.notasapp.data.model.response.NotaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("notas")
    suspend fun crearNota(@Body request: NotaRequest): Response<CrearNotaResponse>

    @GET("notas")
    suspend fun obtenerNotas(): Response<List<NotaResponse>>
}

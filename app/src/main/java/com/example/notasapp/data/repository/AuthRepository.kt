package com.example.notasapp.data.repository

import com.example.notasapp.data.local.SessionManager
import com.example.notasapp.data.model.request.LoginRequest
import com.example.notasapp.data.model.response.LoginResponse
import com.example.notasapp.data.remote.RetrofitClient
import com.example.notasapp.data.remote.leerMensajeError

class AuthRepository {

    private val api = RetrofitClient.api

    suspend fun login(email: String, password: String): Result<LoginResponse> {
        return try {
            val response = api.login(LoginRequest(email, password))
            val body = response.body()
            if (response.isSuccessful && body != null) {
                // se guarda el token para las siguientes peticiones
                SessionManager.guardarSesion(body.token, body.usuario.nombre)
                Result.success(body)
            } else {
                Result.failure(Exception(leerMensajeError(response)))
            }
        } catch (e: Exception) {
            Result.failure(Exception("No se pudo conectar con el servidor"))
        }
    }
}

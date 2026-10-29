package com.example.notasapp.data.repository

import com.example.notasapp.data.model.request.NotaRequest
import com.example.notasapp.data.model.response.CrearNotaResponse
import com.example.notasapp.data.model.response.NotaResponse
import com.example.notasapp.data.remote.RetrofitClient
import com.example.notasapp.data.remote.leerMensajeError

class NotasRepository {

    private val api = RetrofitClient.api

    suspend fun crearNota(titulo: String, contenido: String): Result<CrearNotaResponse> {
        return try {
            val response = api.crearNota(NotaRequest(titulo, contenido))
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.success(body)
            } else {
                Result.failure(Exception(leerMensajeError(response)))
            }
        } catch (e: Exception) {
            Result.failure(Exception("No se pudo conectar con el servidor"))
        }
    }

    suspend fun obtenerNotas(): Result<List<NotaResponse>> {
        return try {
            val response = api.obtenerNotas()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.success(body)
            } else {
                Result.failure(Exception(leerMensajeError(response)))
            }
        } catch (e: Exception) {
            Result.failure(Exception("No se pudo conectar con el servidor"))
        }
    }
}

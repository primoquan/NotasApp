package com.example.notasapp.data.remote

import com.example.notasapp.data.model.response.ErrorResponse
import com.google.gson.Gson
import retrofit2.Response

// lee el mensaje de error que manda el backend
fun leerMensajeError(response: Response<*>): String {
    return try {
        val texto = response.errorBody()?.string()
        Gson().fromJson(texto, ErrorResponse::class.java).mensaje
    } catch (e: Exception) {
        "Error del servidor (${response.code()})"
    }
}

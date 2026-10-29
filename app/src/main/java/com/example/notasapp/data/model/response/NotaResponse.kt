package com.example.notasapp.data.model.response

import com.google.gson.annotations.SerializedName

data class NotaResponse(
    val id: Int,
    @SerializedName("usuario_id") val usuarioId: Int,
    val titulo: String,
    val contenido: String,
    @SerializedName("fecha_creacion") val fechaCreacion: String
)

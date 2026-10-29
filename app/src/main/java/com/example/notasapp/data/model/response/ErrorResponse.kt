package com.example.notasapp.data.model.response

// el backend manda los errores como { "mensaje": "..." }
data class ErrorResponse(
    val mensaje: String
)

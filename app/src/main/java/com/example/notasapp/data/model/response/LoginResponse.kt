package com.example.notasapp.data.model.response

data class LoginResponse(
    val token: String,
    val usuario: UsuarioResponse
)

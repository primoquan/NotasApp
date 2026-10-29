package com.example.notasapp.ui

// estados de cada pantalla: cargando, exito o error
sealed class UiState<out T> {
    object Cargando : UiState<Nothing>()
    data class Exito<T>(val datos: T) : UiState<T>()
    data class Error(val mensaje: String) : UiState<Nothing>()
}

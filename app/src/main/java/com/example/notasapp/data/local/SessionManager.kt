package com.example.notasapp.data.local

import android.content.Context
import android.content.SharedPreferences

// guarda el token y el nombre del usuario en SharedPreferences
object SessionManager {

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences("sesion", Context.MODE_PRIVATE)
    }

    fun guardarSesion(token: String, nombre: String) {
        prefs.edit()
            .putString("token", token)
            .putString("nombre", nombre)
            .apply()
    }

    fun obtenerToken(): String? = prefs.getString("token", null)

    fun obtenerNombre(): String = prefs.getString("nombre", "") ?: ""

    fun cerrarSesion() {
        prefs.edit().clear().apply()
    }
}

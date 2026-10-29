package com.example.notasapp

import android.app.Application
import com.example.notasapp.data.local.SessionManager

class NotasApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // se inicia la sesion al abrir la app para poder leer el token
        SessionManager.init(this)
    }
}

package com.example.notasapp.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.notasapp.R
import com.example.notasapp.data.local.SessionManager
import com.example.notasapp.ui.crearnota.CrearNotaActivity
import com.example.notasapp.ui.login.LoginActivity
import com.example.notasapp.ui.notas.NotasActivity

class DashboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenida)
        val btnCrearNota = findViewById<Button>(R.id.btnCrearNota)
        val btnVerNotas = findViewById<Button>(R.id.btnVerNotas)
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)

        // el nombre se guardo al hacer login
        tvBienvenida.text = "Bienvenido, ${SessionManager.obtenerNombre()}"

        btnCrearNota.setOnClickListener {
            startActivity(Intent(this, CrearNotaActivity::class.java))
        }

        btnVerNotas.setOnClickListener {
            startActivity(Intent(this, NotasActivity::class.java))
        }

        btnCerrarSesion.setOnClickListener {
            SessionManager.cerrarSesion()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}

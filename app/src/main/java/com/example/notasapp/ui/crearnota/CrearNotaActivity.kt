package com.example.notasapp.ui.crearnota

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notasapp.R
import com.example.notasapp.ui.UiState

class CrearNotaActivity : ComponentActivity() {

    private lateinit var viewModel: CrearNotaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_nota)

        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val etContenido = findViewById<EditText>(R.id.etContenido)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        viewModel = ViewModelProvider(this)[CrearNotaViewModel::class.java]

        btnGuardar.setOnClickListener {
            viewModel.guardarNota(etTitulo.text.toString(), etContenido.text.toString())
        }

        viewModel.estado.observe(this) { estado ->
            when (estado) {
                is UiState.Cargando -> {
                    progressBar.visibility = View.VISIBLE
                    btnGuardar.isEnabled = false
                }
                is UiState.Exito -> {
                    progressBar.visibility = View.GONE
                    btnGuardar.isEnabled = true
                    Toast.makeText(this, estado.datos.mensaje, Toast.LENGTH_SHORT).show()
                    // se limpian los campos para escribir otra nota
                    etTitulo.text.clear()
                    etContenido.text.clear()
                }
                is UiState.Error -> {
                    progressBar.visibility = View.GONE
                    btnGuardar.isEnabled = true
                    Toast.makeText(this, estado.mensaje, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

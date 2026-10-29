package com.example.notasapp.ui.notas

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notasapp.R
import com.example.notasapp.ui.UiState

class NotasActivity : ComponentActivity() {

    private lateinit var viewModel: NotasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        val rvNotas = findViewById<RecyclerView>(R.id.rvNotas)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val tvVacio = findViewById<TextView>(R.id.tvVacio)

        val adapter = NotasAdapter()
        rvNotas.layoutManager = LinearLayoutManager(this)
        rvNotas.adapter = adapter

        viewModel = ViewModelProvider(this)[NotasViewModel::class.java]

        viewModel.estado.observe(this) { estado ->
            when (estado) {
                is UiState.Cargando -> {
                    progressBar.visibility = View.VISIBLE
                    tvVacio.visibility = View.GONE
                }
                is UiState.Exito -> {
                    progressBar.visibility = View.GONE
                    adapter.actualizarNotas(estado.datos)
                    // si no hay notas se muestra un mensaje
                    tvVacio.visibility = if (estado.datos.isEmpty()) View.VISIBLE else View.GONE
                }
                is UiState.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, estado.mensaje, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.cargarNotas()
    }
}

package com.example.notasapp.ui.notas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notasapp.R
import com.example.notasapp.data.model.response.NotaResponse

class NotasAdapter : RecyclerView.Adapter<NotasAdapter.NotaViewHolder>() {

    private var notas: List<NotaResponse> = emptyList()

    class NotaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitulo: TextView = view.findViewById(R.id.tvTitulo)
        val tvContenido: TextView = view.findViewById(R.id.tvContenido)
        val tvFecha: TextView = view.findViewById(R.id.tvFecha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(vista)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder.tvTitulo.text = nota.titulo
        holder.tvContenido.text = nota.contenido
        holder.tvFecha.text = nota.fechaCreacion
    }

    override fun getItemCount(): Int = notas.size

    fun actualizarNotas(nuevasNotas: List<NotaResponse>) {
        notas = nuevasNotas
        notifyDataSetChanged()
    }
}

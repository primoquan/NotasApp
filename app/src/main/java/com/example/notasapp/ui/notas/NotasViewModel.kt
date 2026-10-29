package com.example.notasapp.ui.notas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notasapp.data.model.response.NotaResponse
import com.example.notasapp.data.repository.NotasRepository
import com.example.notasapp.ui.UiState
import kotlinx.coroutines.launch

class NotasViewModel : ViewModel() {

    private val repository = NotasRepository()

    private val _estado = MutableLiveData<UiState<List<NotaResponse>>>()
    val estado: LiveData<UiState<List<NotaResponse>>> = _estado

    fun cargarNotas() {
        _estado.value = UiState.Cargando
        viewModelScope.launch {
            val resultado = repository.obtenerNotas()
            _estado.value = resultado.fold(
                onSuccess = { UiState.Exito(it) },
                onFailure = { UiState.Error(it.message ?: "Error al obtener las notas") }
            )
        }
    }
}

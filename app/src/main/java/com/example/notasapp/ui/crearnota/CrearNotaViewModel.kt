package com.example.notasapp.ui.crearnota

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notasapp.data.model.response.CrearNotaResponse
import com.example.notasapp.data.repository.NotasRepository
import com.example.notasapp.ui.UiState
import kotlinx.coroutines.launch

class CrearNotaViewModel : ViewModel() {

    private val repository = NotasRepository()

    private val _estado = MutableLiveData<UiState<CrearNotaResponse>>()
    val estado: LiveData<UiState<CrearNotaResponse>> = _estado

    fun guardarNota(titulo: String, contenido: String) {
        if (titulo.isBlank() || contenido.isBlank()) {
            _estado.value = UiState.Error("Ingrese el título y el contenido")
            return
        }

        _estado.value = UiState.Cargando
        viewModelScope.launch {
            val resultado = repository.crearNota(titulo.trim(), contenido.trim())
            _estado.value = resultado.fold(
                onSuccess = { UiState.Exito(it) },
                onFailure = { UiState.Error(it.message ?: "Error al guardar la nota") }
            )
        }
    }
}

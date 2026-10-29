package com.example.notasapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notasapp.data.model.response.LoginResponse
import com.example.notasapp.data.repository.AuthRepository
import com.example.notasapp.ui.UiState
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _estado = MutableLiveData<UiState<LoginResponse>>()
    val estado: LiveData<UiState<LoginResponse>> = _estado

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _estado.value = UiState.Error("Ingrese el correo y la contraseña")
            return
        }

        _estado.value = UiState.Cargando
        viewModelScope.launch {
            val resultado = repository.login(email.trim(), password)
            _estado.value = resultado.fold(
                onSuccess = { UiState.Exito(it) },
                onFailure = { UiState.Error(it.message ?: "Error al iniciar sesión") }
            )
        }
    }
}

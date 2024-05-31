package com.example.jetmv.ui.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PrincipalViewModel : ViewModel() {
    private val _navigationEvent = MutableStateFlow<String?>(null)
    val navigationEvent: StateFlow<String?> = _navigationEvent

    fun navigateTo(screen: String) {
        _navigationEvent.value = screen
    }

    fun onLogoutClicked() {
        // Lógica para manejar el cierre de sesión
    }
}

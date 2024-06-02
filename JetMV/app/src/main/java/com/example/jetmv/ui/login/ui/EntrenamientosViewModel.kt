package com.example.jetmv.ui.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class EntrenamientosViewModel : ViewModel() {
    private val _selectedDate = MutableLiveData<Date>()
    val selectedDate: LiveData<Date> = _selectedDate

    private val _exercises = MutableLiveData<List<String>>()
    val exercises: LiveData<List<String>> = _exercises

    fun onDateSelected(date: Date) {
        _selectedDate.value = date
        loadExercisesForDate(date)
    }

    private fun loadExercisesForDate(date: Date) {
        // Simula la carga de ejercicios para la fecha seleccionada
        _exercises.value = List(20) { "Ejercicio ${it + 1}" }
    }
}
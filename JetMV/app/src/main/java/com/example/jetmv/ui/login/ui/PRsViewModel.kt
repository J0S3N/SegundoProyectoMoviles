package com.example.jetmv.ui.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PRsViewModel : ViewModel() {
    private val _benchPress = MutableLiveData<Int>()
    val benchPress: LiveData<Int> = _benchPress

    private val _shoulderPress = MutableLiveData<Int>()
    val shoulderPress: LiveData<Int> = _shoulderPress

    private val _snatch = MutableLiveData<Int>()
    val snatch: LiveData<Int> = _snatch

    private val _clean = MutableLiveData<Int>()
    val clean: LiveData<Int> = _clean

    private val _deadlift = MutableLiveData<Int>()
    val deadlift: LiveData<Int> = _deadlift

    fun loadPRs() {
        // Simula la carga de datos preexistentes
        _benchPress.value = 100
        _shoulderPress.value = 80
        _snatch.value = 60
        _clean.value = 90
        _deadlift.value = 120
    }

    fun updateBenchPress(value: Int) {
        _benchPress.value = value
    }

    fun updateShoulderPress(value: Int) {
        _shoulderPress.value = value
    }

    fun updateSnatch(value: Int) {
        _snatch.value = value
    }

    fun updateClean(value: Int) {
        _clean.value = value
    }

    fun updateDeadlift(value: Int) {
        _deadlift.value = value
    }
}

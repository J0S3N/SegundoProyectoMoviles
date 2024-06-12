package com.example.jetmv.ui.login.ui

import android.app.Application
import android.content.ContentValues
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jetmv.ui.db.DatabaseHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PRsViewModel(application: Application) : AndroidViewModel(application) {
    private val dbHelper = DatabaseHelper(application)

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
        viewModelScope.launch(Dispatchers.IO) {
            val db = dbHelper.readableDatabase
            val cursor = db.rawQuery("SELECT bench_press, shoulder_press, snatch, clean, deadlift FROM records_personales WHERE usuario_id = 1", null)
            if (cursor.moveToFirst()) {
                _benchPress.postValue(cursor.getInt(cursor.getColumnIndexOrThrow("bench_press")))
                _shoulderPress.postValue(cursor.getInt(cursor.getColumnIndexOrThrow("shoulder_press")))
                _snatch.postValue(cursor.getInt(cursor.getColumnIndexOrThrow("snatch")))
                _clean.postValue(cursor.getInt(cursor.getColumnIndexOrThrow("clean")))
                _deadlift.postValue(cursor.getInt(cursor.getColumnIndexOrThrow("deadlift")))
            }
            cursor.close()
            db.close()
        }
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

    fun saveAllPRs() {
        viewModelScope.launch(Dispatchers.IO) {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("bench_press", _benchPress.value)
                put("shoulder_press", _shoulderPress.value)
                put("snatch", _snatch.value)
                put("clean", _clean.value)
                put("deadlift", _deadlift.value)
            }
            db.update("records_personales", values, "usuario_id = ?", arrayOf("1"))
            db.close()
        }
    }
}


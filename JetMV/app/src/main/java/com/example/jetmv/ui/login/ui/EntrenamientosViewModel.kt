package com.example.jetmv.ui.login.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jetmv.ui.db.DatabaseHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class EntrenamientosViewModel(application: Application) : AndroidViewModel(application) {
    private val dbHelper = DatabaseHelper(application)

    private val _selectedDate = MutableLiveData<Date>()
    val selectedDate: LiveData<Date> = _selectedDate

    private val _exercises = MutableLiveData<List<String>>()
    val exercises: LiveData<List<String>> = _exercises

    fun onDateSelected(date: Date) {
        _selectedDate.value = date
        loadExercisesForDate(date)
    }

    private fun loadExercisesForDate(date: Date) {
        viewModelScope.launch(Dispatchers.IO) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateString = dateFormat.format(date)
            val exercisesList = mutableListOf<String>()
            val db = dbHelper.readableDatabase
            val cursor = db.rawQuery("SELECT ejercicio FROM entrenamientos WHERE fecha = ?", arrayOf(dateString))

            if (cursor.moveToFirst()) {
                do {
                    val ejercicio = cursor.getString(cursor.getColumnIndexOrThrow("ejercicio"))
                    exercisesList.add(ejercicio)
                } while (cursor.moveToNext())
            }
            cursor.close()
            db.close()
            _exercises.postValue(exercisesList)
        }
    }
}

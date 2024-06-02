package com.example.jetmv.ui.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeightCalculatorViewModel : ViewModel() {
    private val barWeight = 45

    private val _distribution = MutableLiveData<Map<String, Int>>()
    val distribution: LiveData<Map<String, Int>> = _distribution

    fun calculateDistribution(totalWeight: Int) {
        val remainingWeight = totalWeight - barWeight
        if (remainingWeight < 0) {
            _distribution.value = emptyMap()
            return
        }

        val weights = listOf(45.0, 35.0, 25.0, 15.0, 10.0, 5.0, 2.5)
        val weightCounts = mutableMapOf<String, Int>()
        var remaining = remainingWeight.toDouble()

        for (weight in weights) {
            val count = (remaining / (weight * 2)).toInt()
            if (count > 0) {
                weightCounts["Discos de $weight"] = count * 2
                remaining -= count * weight * 2
            }
        }

        _distribution.value = weightCounts
    }
}


package com.example.jetmv.ui.login.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class WeightCalculatorViewModel : ViewModel() {
    private val _weightDistribution = MutableStateFlow<Map<Double, Int>>(emptyMap())
    val weightDistribution: StateFlow<Map<Double, Int>> = _weightDistribution

    fun calculateWeightDistribution(totalWeight: Double) {
        val barWeight = 45.0
        val plateWeights = listOf(45.0, 35.0, 25.0, 15.0, 10.0, 5.0, 2.5)
        var remainingWeight = (totalWeight - barWeight) / 2

        if (remainingWeight < 0) {
            _weightDistribution.value = emptyMap()
            return
        }

        val distribution = mutableMapOf<Double, Int>()

        for (plate in plateWeights) {
            while (remainingWeight >= plate) {
                distribution[plate] = distribution.getOrDefault(plate, 0) + 2
                remainingWeight -= plate
            }
        }

        _weightDistribution.update { distribution }
    }
}
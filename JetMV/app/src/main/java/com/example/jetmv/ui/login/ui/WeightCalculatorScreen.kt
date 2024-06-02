package com.example.jetmv.ui.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WeightCalculatorScreen(viewModel: WeightCalculatorViewModel = viewModel()) {
    val distribution by viewModel.distribution.observeAsState(initial = emptyMap())
    var inputWeight by remember { mutableStateOf("") }
    val weightError = inputWeight.toIntOrNull() == null || inputWeight.toInt() <= 45

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Calculadora de Pesos", style = MaterialTheme.typography.headlineSmall)

        TextField(
            value = inputWeight,
            onValueChange = { inputWeight = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Peso total (incluyendo la barra)") },
            isError = weightError
        )
        if (weightError) {
            Text(
                "Por favor ingrese un número mayor a 45",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val totalWeight = inputWeight.toIntOrNull()
                if (totalWeight != null && totalWeight > 45) {
                    viewModel.calculateDistribution(totalWeight)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !weightError
        ) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Distribución de pesos:", style = MaterialTheme.typography.headlineSmall)
        Text("Barra: 45 lbs", style = MaterialTheme.typography.bodyLarge)

        distribution.forEach { (weight, count) ->
            Text("$weight: $count", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

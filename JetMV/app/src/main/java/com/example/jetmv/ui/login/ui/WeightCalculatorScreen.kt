package com.example.jetmv.ui.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun WeightCalculatorScreen(viewModel: WeightCalculatorViewModel = viewModel(), navController: NavController) {
    val (inputWeight, setInputWeight) = remember { mutableStateOf("") }
    val weightDistribution by viewModel.weightDistribution.collectAsState(initial = emptyMap())
    val (errorMessage, setErrorMessage) = remember { mutableStateOf<String?>(null) }
    val barWeight = 45.0
    val maxWeight = 400.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Calculadora de Pesos", style = MaterialTheme.typography.headlineSmall)

        TextField(
            value = inputWeight,
            onValueChange = setInputWeight,
            label = { Text("Peso Deseado (incluyendo barra)") },
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage != null) {
            Text(errorMessage, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
        }

        Button(
            onClick = {
                val weight = inputWeight.toDoubleOrNull()
                if (weight == null || weight < barWeight || weight > maxWeight) {
                    setErrorMessage("Por favor, ingrese un peso válido entre $barWeight y $maxWeight libras.")
                } else {
                    setErrorMessage(null)
                    viewModel.calculateWeightDistribution(weight)
                }
            },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Distribución de Pesos (incluyendo la barra de $barWeight lbs):", style = MaterialTheme.typography.bodyLarge)
        weightDistribution.forEach { (weight, count) ->
            Text("$weight lbs: $count", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("principal") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Volver a la Pantalla Principal")
        }
    }
}

package com.example.jetmv.ui.login.ui


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@Composable
fun PrincipalScreen(viewModel: PrincipalViewModel = viewModel(), navController: NavController) {
    val navigationEvent by viewModel.navigationEvent.collectAsState()

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Pantalla Principal")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.navigateTo("weight_calculator") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Navegar a Calculadora de Pesos")
            }

            Button(
                onClick = { viewModel.navigateTo("prs") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Navegar a PRs")
            }

            Button(
                onClick = { viewModel.navigateTo("entrenamientos") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Navegar a Entrenamientos")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.onLogoutClicked() },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Salir")
            }
        }

        // Observa los eventos de navegación
        navigationEvent?.let { screen ->
            // Lógica de navegación aquí, usando Navigation Compose
            navController.navigate(screen) {
                popUpTo("principal") { inclusive = true }
            }
        }
    }
}

package com.example.jetmv.ui.login.ui


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun PrincipalScreen(viewModel: PrincipalViewModel = viewModel()) {
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
                onClick = { viewModel.navigateTo("Screen1") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Navegar a Pantalla 1")
            }

            Button(
                onClick = { viewModel.navigateTo("Screen2") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Navegar a Pantalla 2")
            }

            Button(
                onClick = { viewModel.navigateTo("Screen3") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Navegar a Pantalla 3")
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
            // Lógica de navegación aquí, por ejemplo, usando Navigation Compose
            // navController.navigate(screen)
        }
    }
}

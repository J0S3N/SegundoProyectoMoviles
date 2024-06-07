package com.example.jetmv.ui.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(), navController: NavController) {
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val loginResult by viewModel.loginResult.observeAsState()
    val loginEnable by viewModel.loginEnable.observeAsState(initial = false)

    LaunchedEffect(loginResult) {
        if (loginResult == true) {
            navController.navigate("principal") {
                popUpTo("login") { inclusive = true }
            }
        } else if (loginResult == false) {
            // Mostrar mensaje de error si es necesario
            // Esto puede ser un Snackbar, Toast, etc.
        }
    }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        } else {
            Column(modifier = Modifier.align(Alignment.Center)) {
                val email by viewModel.email.observeAsState("")
                val password by viewModel.password.observeAsState("")
                val coroutineScope = rememberCoroutineScope()

                Text("Login", style = MaterialTheme.typography.headlineSmall)

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = email,
                    onValueChange = { viewModel.onLoginChanged(it, password) },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = password,
                    onValueChange = { viewModel.onLoginChanged(email, it) },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.onLoginSelected()
                        }
                    },
                    enabled = loginEnable,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Iniciar Sesión")
                }
            }
        }
    }
}

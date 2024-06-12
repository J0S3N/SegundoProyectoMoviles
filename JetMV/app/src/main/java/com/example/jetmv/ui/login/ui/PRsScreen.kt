package com.example.jetmv.ui.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun PRsScreen(viewModel: PRsViewModel = viewModel(), navController: NavController) {
    val benchPress by viewModel.benchPress.observeAsState(initial = 0)
    val shoulderPress by viewModel.shoulderPress.observeAsState(initial = 0)
    val snatch by viewModel.snatch.observeAsState(initial = 0)
    val clean by viewModel.clean.observeAsState(initial = 0)
    val deadlift by viewModel.deadlift.observeAsState(initial = 0)

    LaunchedEffect(Unit) {
        viewModel.loadPRs()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Records Personales", style = MaterialTheme.typography.headlineSmall)

        PRInputField("Bench Press", benchPress) { value ->
            viewModel.updateBenchPress(value)
        }
        PRInputField("Shoulder Press", shoulderPress) { value ->
            viewModel.updateShoulderPress(value)
        }
        PRInputField("Snatch", snatch) { value ->
            viewModel.updateSnatch(value)
        }
        PRInputField("Clean", clean) { value ->
            viewModel.updateClean(value)
        }
        PRInputField("Deadlift", deadlift) { value ->
            viewModel.updateDeadlift(value)
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

@Composable
fun PRInputField(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit
) {
    var text by remember { mutableStateOf(value.toString()) }

    Column {
        Text(text = label)
        TextField(
            value = text,
            onValueChange = {
                text = it
                val intValue = it.toIntOrNull() ?: 0
                onValueChange(intValue)
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

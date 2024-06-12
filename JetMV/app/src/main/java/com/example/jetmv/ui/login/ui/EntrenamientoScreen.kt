package com.example.jetmv.ui.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun EntrenamientosScreen(viewModel: EntrenamientosViewModel = viewModel(), navController: NavController) {
    val selectedDate by viewModel.selectedDate.observeAsState()
    val exercises by viewModel.exercises.observeAsState(emptyList())

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Selecciona una fecha para los entrenamientos")

            Spacer(modifier = Modifier.height(16.dp))

            DatePickerButton(selectedDate) { date ->
                viewModel.onDateSelected(date)
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(exercises) { exercise ->
                    Text(exercise, modifier = Modifier.padding(8.dp))
                }
            }

            Button(
                onClick = { navController.navigate("principal") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) {
                Text("Volver a la Pantalla Principal")
            }
        }
    }
}

@Composable
fun DatePickerButton(
    selectedDate: Date?,
    onDateSelected: (Date) -> Unit
) {
    val context = LocalContext.current
    val dateFormatter = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }
    val calendar = Calendar.getInstance()

    selectedDate?.let { calendar.time = it }

    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            onDateSelected(calendar.time)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Button(onClick = { datePickerDialog.show() }) {
        Text(text = selectedDate?.let { dateFormatter.format(it) } ?: "Selecciona una fecha")
    }

}
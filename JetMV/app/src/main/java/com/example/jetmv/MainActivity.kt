package com.example.jetmv

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetmv.ui.db.DatabaseHelper
import com.example.jetmv.ui.login.ui.EntrenamientosScreen
import com.example.jetmv.ui.login.ui.EntrenamientosViewModel
import com.example.jetmv.ui.login.ui.LoginScreen
import com.example.jetmv.ui.login.ui.LoginViewModel
import com.example.jetmv.ui.login.ui.LoginViewModelFactory
import com.example.jetmv.ui.login.ui.PRsScreen
import com.example.jetmv.ui.login.ui.PRsViewModel
import com.example.jetmv.ui.login.ui.PrincipalScreen
import com.example.jetmv.ui.login.ui.PrincipalViewModel
import com.example.jetmv.ui.login.ui.WeightCalculatorScreen
import com.example.jetmv.ui.login.ui.WeightCalculatorViewModel
import com.example.jetmv.ui.theme.JetMVTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar la base de datos
        val dbHelper = DatabaseHelper(this)
        dbHelper.writableDatabase

        setContent {
            JetMVTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            val application = LocalContext.current.applicationContext as Application
            val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(application))
            LoginScreen(loginViewModel, navController)
        }
        composable("principal") {
            PrincipalScreen(viewModel = viewModel(), navController = navController)
        }
        composable("weight_calculator") {
            WeightCalculatorScreen(viewModel = viewModel())
        }
        composable("prs") {
            PRsScreen(viewModel = viewModel())
        }
        composable("entrenamientos") {
            EntrenamientosScreen(viewModel = viewModel())
        }
    }
}
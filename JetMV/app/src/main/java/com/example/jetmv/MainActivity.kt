package com.example.jetmv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetmv.ui.login.ui.EntrenamientosScreen
import com.example.jetmv.ui.login.ui.EntrenamientosViewModel
import com.example.jetmv.ui.login.ui.LoginScreen
import com.example.jetmv.ui.login.ui.LoginViewModel
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
            LoginScreen(LoginViewModel(), navController)
        }
        composable("principal") {
            PrincipalScreen(PrincipalViewModel())
        }
        composable("entrenamientos") {
            EntrenamientosScreen(EntrenamientosViewModel())
        }
        composable("prs") {
            PRsScreen(PRsViewModel())
        }
        composable("weight_calculator") {
            WeightCalculatorScreen(WeightCalculatorViewModel())
        }
    }
}

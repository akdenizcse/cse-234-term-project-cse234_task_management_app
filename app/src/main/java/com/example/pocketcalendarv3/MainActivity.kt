package com.example.pocketcalendarv3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pocketcalendarv3.ui.theme.PocketCalendarV3Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocketCalendarV3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChangePage()
                }
            }
        }
    }
}

@Composable
fun ChangePage(){
    val navController = rememberNavController()

    NavHost(navController = navController , startDestination = "LoginPage"){
        composable(route = "LoginPage"){
            LoginPageView(navController = navController)
        }
        composable(route = "ForgotYourPasswordPage"){
            ForgetYourPasswordView(navController = navController)
        }
        composable(route = "RegisterPage"){
            RegisterPageView(navController = navController)
        }
        composable(route = "MainPage"){
            MainPage(navController = navController)
        }
    }
}
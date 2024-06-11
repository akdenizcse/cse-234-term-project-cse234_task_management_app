package com.example.pocketcalendarv3

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocketCalendarV3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ChangePage()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChangePage() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "LoginPage") {
        composable(route = "LoginPage") {
            LoginPageView(navController = navController)
        }
        composable(route = "ForgotYourPasswordPage") {
            ForgetYourPasswordView(navController = navController)
        }
        composable(route = "RegisterPage") {
            RegisterPageView(navController = navController)
        }
        composable(route = "MainPage/{loggedInUserEmail}") { backStackEntry ->
            val loggedInUserEmail = backStackEntry.arguments?.getString("loggedInUserEmail")
            MainPage(navController = navController, loggedInUserEmail = loggedInUserEmail)
        }
        composable(route = "UserPage/{loggedInUserEmail}") { backStackEntry ->
            val loggedInUserEmail = backStackEntry.arguments?.getString("loggedInUserEmail")
            UserPageView(navController = navController, loggedInUserEmail = loggedInUserEmail)
        }
        composable(route = "DetailPriorityTask/{loggedInUserEmail}/{title}") { backStackEntry ->
            val loggedInUserEmail = backStackEntry.arguments?.getString("loggedInUserEmail")
            val title = backStackEntry.arguments?.getString("title")
            DetailPriorityTask(
                navController = navController,
                loggedInUserEmail = loggedInUserEmail,
                title = title
            )
        }
        composable(route = "EditPriorityTask/{loggedInUserEmail}/{title}") { backStackEntry ->
            val loggedInUserEmail = backStackEntry.arguments?.getString("loggedInUserEmail")
            val title = backStackEntry.arguments?.getString("title")

            EditPriorityTask(
                navController = navController,
                loggedInUserEmail = loggedInUserEmail,
                title = title
            )
        }
        composable(route = "ChangePasswordPage/{loggedInUserEmail}") { backStackEntry ->
            val loggedInUserEmail = backStackEntry.arguments?.getString("loggedInUserEmail")
            ChangePasswordPageView(
                navController = navController, loggedInUserEmail = loggedInUserEmail
            )
        }
        composable(route = "AddtoDoListPage/{loggedInUserEmail}/{title}") { backStackEntry ->
            val loggedInUserEmail = backStackEntry.arguments?.getString("loggedInUserEmail")
            val title = backStackEntry.arguments?.getString("title")
            AddtoDoListPage(navController = navController ,loggedInUserEmail = loggedInUserEmail, title = title)
        }
        composable(route = "EdittoDoListPage") {
            EdittoDoListPage(navController = navController)
        }
        composable(route = "MyProfilePageView") {
            MyProfilePageView(navController = navController)
        }
        composable(route = "EditDailyTaskPage/{loggedInUserEmail}/{title}") { backStackEntry ->
            val loggedInUserEmail = backStackEntry.arguments?.getString("loggedInUserEmail")
            val title = backStackEntry.arguments?.getString("title")
            EditDailyTaskPage(navController = navController, loggedInUserEmail = loggedInUserEmail , title = title)
        }
    }
}
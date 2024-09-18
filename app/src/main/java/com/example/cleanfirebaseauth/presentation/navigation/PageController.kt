package com.example.cleanfirebaseauth.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cleanfirebaseauth.Screen
import com.example.cleanfirebaseauth.presentation.home_page.views.HomePage
import com.example.cleanfirebaseauth.presentation.login_page.views.LoginPage
import com.example.cleanfirebaseauth.presentation.view.RegisterPage
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AuthNavigation() {
    val navController= rememberNavController()
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser
    var startDestination by remember { mutableStateOf(Screen.RegisterPage.route) }


    LaunchedEffect (currentUser){
        startDestination=if (currentUser!=null){
            Screen.HomePage.route
        }else{
            Screen.RegisterPage.route
        }
    }


    NavHost(navController = navController, startDestination =startDestination ) {
        composable(Screen.RegisterPage.route){
            RegisterPage(navController)
        }
        composable(Screen.LoginPage.route){
            LoginPage(navController = navController)
        }
        composable(Screen.HomePage.route){
            HomePage(navController)
        }
    }
}
package com.example.cleanfirebaseauth.presentation.home_page.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cleanfirebaseauth.Screen

@Composable
fun HomePage(
    navController: NavController,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        Text(text = "Welcome HomePage", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Button(onClick = {
            navController.navigate(Screen.LoginPage.route)
        }) {
            Text(text = "LogOut")
        }
    }
}
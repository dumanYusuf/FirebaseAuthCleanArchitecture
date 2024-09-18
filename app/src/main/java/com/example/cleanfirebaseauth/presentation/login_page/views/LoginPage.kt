package com.example.cleanfirebaseauth.presentation.login_page.views


import CustomTextField
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cleanfirebaseauth.Screen
import com.example.cleanfirebaseauth.presentation.login_page.LoginViewModel
import com.example.cleanfirebaseauth.presentation.register_page.RegisterViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginPage(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var userName by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val state = viewModel.authState.collectAsState().value

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigate(Screen.HomePage.route) {
                popUpTo(Screen.LoginPage.route) { inclusive = true }
            }
        }
    }

    LaunchedEffect(state.errorMessage) {
        if (state.errorMessage.isNotBlank()) {
            errorMessage = state.errorMessage
        }
    }

    Scaffold(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    // Başlık
                    Text(
                        text = "Login",
                        fontSize = 32.sp,
                        color = Color(0xFF6200EE),
                        modifier = Modifier.padding(bottom = 20.dp)
                    )

                    // Kullanıcı adı giriş alanı
                    CustomTextField(
                        value = userName,
                        onValueChange = { userName = it },
                        label = "UserName",
                    )

                    Spacer(modifier = Modifier.size(15.dp))

                    // Şifre giriş alanı
                    CustomTextField(
                        value = userPassword,
                        onValueChange = { userPassword = it },
                        label = "UserName",
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.size(25.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Giriş butonu
                        Button(
                            onClick = {
                                errorMessage = ""
                                viewModel.login(userName, userPassword)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF6200EE), // Buton rengi
                                contentColor = Color.White
                            ),
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = "Login")
                        }

                        // Giriş sayfasına yönlendirme butonu
                        TextButton(
                            onClick = {
                                navController.navigate(Screen.RegisterPage.route)
                            },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = "Sign Up?",
                                color = Color(0xFF6200EE)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(15.dp))

                    // Başarılı giriş durumu
                    if (state.isSuccess) {
                        Text(
                            text = "Login Successful!",
                            color = Color.Green,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }

                    // Yüklenme durumu
                    if (state.loading) {
                        CircularProgressIndicator(
                            color = Color(0xFF6200EE),
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }

                    // Hata mesajı
                    if (errorMessage.isNotBlank()) {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                        )
                    }
                }
            }
        }
    )
}

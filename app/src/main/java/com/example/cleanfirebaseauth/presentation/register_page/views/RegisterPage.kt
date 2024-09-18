package com.example.cleanfirebaseauth.presentation.view

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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cleanfirebaseauth.Screen
import com.example.cleanfirebaseauth.presentation.register_page.RegisterViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterPage(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    var userName by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var userConfirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val state = viewModel.authState.collectAsState().value

    fun validateAndRegister() {
        errorMessage = ""

        if (!userName.contains("@")) {
            errorMessage = "Invalid email address. Must contain '@'."
            return
        }

        if (userPassword.length < 6) {
            errorMessage = "Password must be at least 6 characters."
            return
        }
        if (userPassword != userConfirmPassword) {
            errorMessage = "Passwords do not match."
            return
        }
        viewModel.register(userName, userPassword)
    }

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigate(Screen.HomePage.route) {
                popUpTo(Screen.RegisterPage.route) { inclusive = true }
            }
        }
    }

    Scaffold(
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Başlık
                    Text(
                        text = "Register",
                        fontSize = 32.sp,
                        color = Color(0xFF6200EE),
                        modifier = Modifier.padding(bottom = 20.dp)
                    )

                    // Kullanıcı adı giriş alanı
                    CustomTextField(
                        value = userName,
                        onValueChange = { userName = it },
                        label = "Email"
                    )

                    Spacer(modifier = Modifier.size(15.dp))

                    // Şifre giriş alanı
                    CustomTextField(
                        value = userPassword,
                        onValueChange = { userPassword = it },
                        label = "Password",
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.size(15.dp))

                    // Şifre onay giriş alanı
                    CustomTextField(
                        value = userConfirmPassword,
                        onValueChange = { userConfirmPassword = it },
                        label = "Confirm Password",
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.size(25.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Kayıt butonu
                        Button(
                            onClick = {
                                validateAndRegister()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF6200EE),
                                contentColor = Color.White
                            ),
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = "Register")
                        }

                        // Giriş sayfasına yönlendirme butonu
                        TextButton(
                            onClick = {
                                navController.navigate(Screen.LoginPage.route)
                            },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = "Log in?",
                                color = Color(0xFF6200EE)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(15.dp))

                    // Başarılı kayıt durumu
                    if (state.isSuccess) {
                        Text(
                            text = "Registration Successful!",
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


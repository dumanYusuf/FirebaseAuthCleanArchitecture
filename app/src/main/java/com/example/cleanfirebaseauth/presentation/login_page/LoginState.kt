package com.example.cleanfirebaseauth.presentation.login_page

data class LoginState(
    val errorMessage:String ="",
    val loading:Boolean =false,
    val isSuccess:Boolean=false
)

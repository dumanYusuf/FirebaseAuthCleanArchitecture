package com.example.cleanfirebaseauth.presentation.register_page

data class RegisterState(
    val errorMessage:String ="",
    val loading:Boolean =false,
    val isSuccess:Boolean=false
)

package com.example.cleanfirebaseauth

sealed class Screen(val route:String) {

    object RegisterPage:Screen("register_page")
    object LoginPage:Screen("login_page")
    object HomePage:Screen("home_page")


}
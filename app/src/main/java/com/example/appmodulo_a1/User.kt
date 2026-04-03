package com.example.appmodulo_a1

data class User(
    val login: String ,
    val email: String,
    val password: String
)

val currentUser = User(
    login = "",
    email = "",
    password = ""
)



package com.example.app.models

data class LoginResponse (
    val user: User,
    val token: String
)
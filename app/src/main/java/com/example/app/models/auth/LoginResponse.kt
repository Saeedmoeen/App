package com.example.app.models.auth

data class LoginResponse (
    val search: User,
    val token: String
)
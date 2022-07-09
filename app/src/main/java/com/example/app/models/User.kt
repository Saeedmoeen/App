package com.example.app.models

data class User(
    val email: String,
    val first_name: String,
    val groups: List<Any>,
    val id: Int,
    val last_name: String,
    val permissions: List<Any>,
    val token: String,
    val username: String
)
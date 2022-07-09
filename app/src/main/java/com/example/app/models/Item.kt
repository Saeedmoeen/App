package com.example.app.models

data class Item(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)
package com.example.app.models

data class Price(
    val discount_percent: Double,
    val discount_value: Int,
    val final_price: Int,
    val mid: Int,
    val price: Int,
    val price_level: Int,
    val price_type: Int
)
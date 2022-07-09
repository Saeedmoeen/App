package com.example.app.models

data class Result(
    val active: Boolean,
    val barcodes: List<Barcode>,
    val category: Category,
    val code: Int,
    val info: String,
    val mid: Int,
    val name: String,
    val prices: List<Price>,
    val quantities: List<Quantity>,
    val taxable: Boolean,
    val unit1: Any,
    val unit2: String,
    val unit2_in_unit1: Any,
    val unit_type: Int,
    val visitor_percent: Any
)
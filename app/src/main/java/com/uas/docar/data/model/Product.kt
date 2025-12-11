package com.uas.docar.data.model

data class Product(
    val id: Int,
    val name: String,
    val price: Int,
    val imageUrl: String,
    val description: String,
    val isAvailable: Boolean = true
)
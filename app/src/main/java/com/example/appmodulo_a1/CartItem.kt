package com.example.appmodulo_a1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class CartItem(
    val product: Product,
    var quantity: Int = 1
) {
    var quantityState by mutableStateOf(quantity)
}
package com.example.appmodulo_a1

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String
)

val sampleProducts = listOf(
    Product(id = 0, name = "Ноутбук",  description = "Мощный ноутбук", price = "250 000 ₸"),
    Product(id = 1, name = "Айфон", description = "Айфон 20 про ультра", price = "1 000 000 ₸"),
    Product(id = 2, name = "Планшет",  description = "Планшет для учёбы", price = "220 000 ₸"),
    Product(id = 3, name = "Аирподс", description = "Беспроводные наушники", price = "95 000 ₸")
)
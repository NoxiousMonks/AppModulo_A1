package com.example.appmodulo_a1

import java.util.UUID

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val image: List<Int>
)
val images1 = listOf(
    R.drawable.coder,
    R.drawable.itguy,
    R.drawable.hacker
)

val images2 = listOf(
    R.drawable.itguy,
    R.drawable.coder,
    R.drawable.hacker
)

val images3 = listOf(
    R.drawable.hacker,
    R.drawable.coder,
    R.drawable.itguy,
)

val images4 = listOf(
    R.drawable.diegotheworld,
    R.drawable.itguy,
    R.drawable.hacker
)

val images5 = listOf(
    R.drawable.cat,
    R.drawable.itguy,
    R.drawable.hacker
)
val sampleProducts = listOf(
    Product(id = 0, name = "Ноутбук",  description = "Мощный ноутбук", price = "250 000 ₸", image = images1 ),
    Product(id = 1, name = "Айфон", description = "Айфон 20 про ультра", price = "1 000 000 ₸",image = images2 ),
    Product(id = 2, name = "Планшет",  description = "Планшет для учёбы", price = "220 000 ₸", image = images3),
    Product(id = 3, name = "Аирподс", description = "Беспроводные наушники", price = "95 000 ₸", image = images4),
    Product(id = 4, name = "Аирподс", description = "Беспроводные наушники", price = "95 000 ₸", image = images5)
)



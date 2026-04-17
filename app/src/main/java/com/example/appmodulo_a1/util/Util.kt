package com.example.appmodulo_a1.util

import android.content.SharedPreferences
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.core.content.edit
import com.example.appmodulo_a1.CartItem
import kotlin.collections.forEach

fun String.deserialisation(): List<Pair<Int, Int>>? =
    try {
        this.split(';').map {
            val parts: List<String> = it.split(',')
            Pair(parts[0].toInt(), parts[1].toInt())
        }
    } catch (e: Exception) {
        null
    }


fun List<Pair<Int, Int>>.serialisation(): String {
    val sb = StringBuilder()
    this.forEach {
        sb.append(it.first.toString())
        sb.append(',')
        sb.append(it.second.toString())
        sb.append(';')
    }
    return sb.toString().removeSuffix(";");
}

fun SharedPreferences.saveCardItems(
    cartItems: SnapshotStateList<CartItem>,
    key: String
) {
    val list: List<Pair<Int, Int>> =
        cartItems.map { item -> Pair(item.product.id, item.quantityState) }
    val string = list.serialisation()
    this.edit { putString(key, string) }
}


fun SharedPreferences.loadCardItems(key:String): List<Pair<Int, Int>>? = this.getString(key, "")?.deserialisation()
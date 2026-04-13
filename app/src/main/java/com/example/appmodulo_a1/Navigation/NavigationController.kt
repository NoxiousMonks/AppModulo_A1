package com.example.appmodulo_a1.Navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.appmodulo_a1.Screens.Screens

// Простой контроллер навигации — без ViewModel, просто класс
import com.example.appmodulo_a1.Product
import com.example.appmodulo_a1.Screens.CartItem

class NavigationController {

    private val navStack = mutableStateListOf<Screens>(Screens.LoginScreen)


    val currentScreen: Screens
        get() = navStack.last()



    var selectedProduct by mutableStateOf<Product?>(null)

    var cartItems = mutableStateListOf<CartItem>()
        private set

    fun addToCart(product: Product) {
        val productExistence = cartItems.find { it.product.id == product.id }

        if (productExistence != null) {
            productExistence.quantityState++
        } else {
            cartItems.add(CartItem(product))
        }
    }

    fun increaseQuantity(product: Product) {
        cartItems.find { it.product.id == product.id }?.quantityState++
    }

    fun decreaseQuantity(product: Product) {
        val item = cartItems.find { it.product.id == product.id }

        if (item != null) {
            if (item.quantityState > 1) {
                item.quantityState--
            } else {
                cartItems.remove(item)
            }
        }
    }

    fun clearCart() {
        cartItems.clear()
    }

    fun getTotalPrice(): Int {
        return cartItems.sumOf {
            val price = it.product.price
                .replace("₸", "")
                .replace(" ", "")
                .toInt()

            price * it.quantity
        }
    }





    fun navigateTo(screen: Screens) {
        navStack.add(screen)
    }

    fun navigateBack(): Boolean {
        if (navStack.size > 1) {
            navStack.removeLastOrNull()
            return true
        }
        return false
    }

}
package com.example.appmodulo_a1.Navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.appmodulo_a1.Screens.Screens

// Простой контроллер навигации — без ViewModel, просто класс
import com.example.appmodulo_a1.Product

class NavigationController {

    private val navStack = mutableStateListOf<Screens>(Screens.LoginScreen)


    val currentScreen: Screens
        get() = navStack.last()

    var selectedProduct by mutableStateOf<Product?>(null)

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
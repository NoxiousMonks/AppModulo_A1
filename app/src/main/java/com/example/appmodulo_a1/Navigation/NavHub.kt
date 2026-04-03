package com.example.appmodulo_a1.Navigation

import com.example.appmodulo_a1.Screens.Screens
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.appmodulo_a1.currentUser
import com.example.appmodulo_a1.Screens.CartScreen
import com.example.appmodulo_a1.Screens.CatalogScreen
import com.example.appmodulo_a1.Screens.DetailScreen
import com.example.appmodulo_a1.Screens.LoginScreen
import com.example.appmodulo_a1.Screens.ProfileScreen
import com.example.appmodulo_a1.Screens.RegisterScreen

@Composable
fun NavHub() {
    val nav = remember { NavigationController() }

    when (nav.currentScreen) {

        Screens.LoginScreen -> LoginScreen(
            onLogin = {
                nav.navigateTo(Screens.CatalogScreen)
            },
            onGoToRegister = {
                nav.navigateTo(Screens.RegisterScreen)
            }
        )

        Screens.RegisterScreen -> RegisterScreen(
            onRegister = {
                nav.navigateTo(Screens.CatalogScreen)
            },
            onBack = {
                nav.navigateBack()
            }
        )

        Screens.CatalogScreen -> CatalogScreen(
            onProductClick = { product ->
                nav.selectedProduct = product
                nav.navigateTo(Screens.DetailScreen)
            },
            onGoCart = { nav.navigateTo(Screens.CartScreen) },
            onGoProfile = { nav.navigateTo(Screens.ProfileScreen) }
        )

        Screens.DetailScreen -> DetailScreen(
            product = nav.selectedProduct!!,
            onAddToCart = { nav.navigateTo(Screens.CartScreen) },
            onBack = { nav.navigateBack() }
        )


        Screens.CartScreen -> CartScreen(
            onGoToCatalog = {
                nav.navigateTo(Screens.CatalogScreen)
            },
            onBack = {
                nav.navigateBack()
            }
        )

        Screens.ProfileScreen -> ProfileScreen(
            user = currentUser,
            onLogout = {
                nav.navigateTo(Screens.LoginScreen)
            },
            onBack = {
                nav.navigateBack()
            }
        )
    }
}
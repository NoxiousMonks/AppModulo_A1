package com.example.appmodulo_a1.Navigation

import com.example.appmodulo_a1.Screens.Screens
import androidx.compose.runtime.Composable
import com.example.app1.models.MainViewModel
import com.example.appmodulo_a1.Screens.CartScreen
import com.example.appmodulo_a1.Screens.CatalogScreen
import com.example.appmodulo_a1.Screens.DetailScreen
import com.example.appmodulo_a1.Screens.LoginScreen
import com.example.appmodulo_a1.Screens.ProfileScreen
import com.example.appmodulo_a1.Screens.RegisterScreen

@Composable
fun NavHub(viewModel: MainViewModel,  navCtrl: NavigationController) {
    val screen: Screens = navCtrl.currentScreen

    when (screen) {

        Screens.LoginScreen -> LoginScreen(
            navigateToLogin = {
                navCtrl.navigateTo(Screens.CatalogScreen)
            },
            navigateToRegister = {
                navCtrl.navigateTo(Screens.RegisterScreen)
            },
            login = { email, pass ->
                viewModel.login(email, pass)
            }
        )

        Screens.RegisterScreen -> RegisterScreen(
            onRegister = { newUser ->
                viewModel.saveData(newUser)
                navCtrl.navigateTo(Screens.CatalogScreen)
            },
            onBack = {
                navCtrl.navigateBack()
            }
        )

        Screens.CatalogScreen -> CatalogScreen(
            onProductClick = { product ->
                viewModel.selectedProduct = product
                navCtrl.navigateTo(Screens.DetailScreen)
            },
            onGoCart = { navCtrl.navigateTo(Screens.CartScreen) },
            onGoProfile = { navCtrl.navigateTo(Screens.ProfileScreen) },
            navCtrl = navCtrl
        )

        Screens.DetailScreen -> DetailScreen(
            product = viewModel.selectedProduct!!,
            onAddToCart = {
                viewModel.addToCart(viewModel.selectedProduct!!)

                navCtrl.navigateTo(Screens.CatalogScreen)
            },
            onBack = { navCtrl.navigateBack() },
            viewModel = viewModel
        )


        Screens.CartScreen -> CartScreen(
            onGoToCatalog = {
                navCtrl.navigateTo(Screens.CatalogScreen)
            },
            onBack = {
                navCtrl.navigateBack()
            },
            viewModel = viewModel,
            navCtrl = navCtrl
        )

        Screens.ProfileScreen -> ProfileScreen(
            user = viewModel.loadData(),
            onLogout = {
                navCtrl.navigateTo(Screens.LoginScreen)
            },
            onBack = {
                navCtrl.navigateBack()
            },
            navCtrl = navCtrl
        )
    }
}
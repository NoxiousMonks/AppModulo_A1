package com.example.appmodulo_a1.Navigation

import com.example.appmodulo_a1.Screens.Screens
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.app1.models.MainViewModel
import com.example.appmodulo_a1.Screens.CartScreen
import com.example.appmodulo_a1.Screens.CatalogScreen
import com.example.appmodulo_a1.Screens.DetailScreen
import com.example.appmodulo_a1.Screens.LoginScreen
import com.example.appmodulo_a1.Screens.ProfileScreen
import com.example.appmodulo_a1.Screens.RegisterScreen
import com.example.appmodulo_a1.User

@Composable
fun NavHub(viewModel: MainViewModel,  navCtrl: NavigationController) {
//    val nav = remember { NavigationController() }
    val screen: Screens = navCtrl.currentScreen

    when (screen) {

        Screens.LoginScreen -> LoginScreen(
//            data = viewModel.userData,
            onLogin = {
                navCtrl.navigateTo(Screens.CatalogScreen)
            },
            onGoToRegister = {
                navCtrl.navigateTo(Screens.RegisterScreen)
            },
            login = { email, pass ->
                viewModel.login(email, pass)
            }
        )

        Screens.RegisterScreen -> RegisterScreen(
//            initialData = viewModel.userData,
            onRegister = { newUser ->
                viewModel.saveData(newUser)
//                viewModel.userData = newUser
                navCtrl.navigateTo(Screens.CatalogScreen)
            },
            onBack = {
                navCtrl.navigateBack()
            }
        )

        Screens.CatalogScreen -> CatalogScreen(
            onProductClick = { product ->
                navCtrl.selectedProduct = product
                navCtrl.navigateTo(Screens.DetailScreen)
            },
            onGoCart = { navCtrl.navigateTo(Screens.CartScreen) },
            onGoProfile = { navCtrl.navigateTo(Screens.ProfileScreen) },
            navCtrl = navCtrl
        )

        Screens.DetailScreen -> DetailScreen(
            product = navCtrl.selectedProduct!!,
            onAddToCart = { navCtrl.navigateTo(Screens.CartScreen) },
            onBack = { navCtrl.navigateBack() }
        )


        Screens.CartScreen -> CartScreen(
            onGoToCatalog = {
                navCtrl.navigateTo(Screens.CatalogScreen)
            },
            onBack = {
                navCtrl.navigateBack()
            },
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
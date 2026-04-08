package com.example.appmodulo_a1.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appmodulo_a1.Navigation.NavigationController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    onGoToCatalog: () -> Unit,
    onBack: () -> Unit,
    navCtrl: NavigationController
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navCtrl.navigateTo(Screens.CatalogScreen) },
                    label = { Text("Каталог") },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Каталог") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = {  },
                    label = { Text("Корзина") },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Корзина") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navCtrl.navigateTo(Screens.ProfileScreen) },
                    label = { Text("Профиль") },
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Профиль") }
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Корзина пока пустая")

                Button(onClick = onGoToCatalog) {
                    Text("В каталог")
                }
            }
        }
    }
}
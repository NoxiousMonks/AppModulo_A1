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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.appmodulo_a1.Navigation.NavigationController
import com.example.appmodulo_a1.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    user: User,
    onLogout: () -> Unit,
    onBack: () -> Unit,
    navCtrl: NavigationController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Профиль") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navCtrl.navigateTo(Screens.CatalogScreen) },
                    label = { Text("Каталог") },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Каталог") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navCtrl.navigateTo(Screens.CartScreen) },
                    label = { Text("Корзина") },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Корзина") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = {  },
                    label = { Text("Профиль") },
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Профиль") }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(user.login, style = MaterialTheme.typography.headlineSmall)

            Text(
                user.email,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(onClick = onLogout) {
                Text("Выйти", color = Color.Red)
            }
        }
    }
}
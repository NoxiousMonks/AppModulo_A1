package com.example.appmodulo_a1.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

                title = {
                    Text(
                        "Профиль",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                contentColor = Color.Black
            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = { navCtrl.navigateTo(Screens.CatalogScreen) },
                    label = { Text("Каталог") },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Каталог") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF6149FF),
                        selectedTextColor = Color(0xFF6149FF)
                    )
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navCtrl.navigateTo(Screens.CartScreen) },
                    label = { Text("Корзина") },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Корзина") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF6149FF),
                        selectedTextColor = Color(0xFF6149FF)
                    )
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    label = { Text("Профиль") },
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Профиль") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor =  MaterialTheme.colorScheme.primary,
                        selectedTextColor =  MaterialTheme.colorScheme.primary,
                        indicatorColor  = Color.Transparent

                    )

                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF3F3F3))
                .padding(padding)
//                .padding(24.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))


            Icon(
                Icons.Filled.AccountCircle, contentDescription = "profile",
                modifier = Modifier.size(100.dp)
            )


            Text(user.login, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)

            Text(
                user.email,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .background(Color.White),
                contentAlignment = Alignment.CenterStart
            ) {
                TextButton(onClick = onLogout) {
                    Text("Выйти", color = Color.Red)
                }
            }
        }
    }
}

package com.example.appmodulo_a1.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appmodulo_a1.ImageSlider
import com.example.appmodulo_a1.Navigation.NavigationController
import com.example.appmodulo_a1.Product
import com.example.appmodulo_a1.images
import com.example.appmodulo_a1.sampleProducts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    onProductClick: (Product) -> Unit,
    onGoCart: () -> Unit,
    onGoProfile: () -> Unit,
    navCtrl: NavigationController
) {
    var searching by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                contentColor = Color.Black
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
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
                    selected = false,
                    onClick = { navCtrl.navigateTo(Screens.ProfileScreen) },
                    label = { Text("Профиль") },
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Профиль") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF6149FF),
                        selectedTextColor = Color(0xFF6149FF)
                    )

                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
//                .padding(10.dp)
//                .verticalScroll(rememberScrollState())
                .background(Color(0xFFE5E5EA))
            ,
        ) {
            OutlinedTextField(
                value = searching,
                onValueChange = { searching = it },
                label = { Text("Поиск") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                shape = MaterialTheme.shapes.large,
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Поиск") }
            )

            Spacer(modifier = Modifier.height(16.dp))

//            for (product in sampleProducts) {
//                    ProductCard(
//                        product = product,
//                        onClick = { onProductClick(product) }
//                    )
//                    Spacer(modifier = Modifier.height(12.dp))
//            }

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalItemSpacing = 12.dp
            ) {
                items(
                    items = sampleProducts,
                    key = { it.id }
                ) { product ->
                    ProductCard(
                        product,
                        onClick = { onProductClick(product) }
                    )
                }
            }

        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
//            Surface(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(100.dp),
//                color = Color.LightGray,
//            ) {}
            ImageSlider(images)


            Spacer(modifier = Modifier.height(8.dp))

            Text(product.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(product.description, color = Color.Gray)
        }
    }
}


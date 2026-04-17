package com.example.appmodulo_a1.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appmodulo_a1.ui.theme.ImageSlider
import com.example.appmodulo_a1.Navigation.NavigationController
import com.example.appmodulo_a1.Product
import com.example.appmodulo_a1.R
//import com.example.appmodulo_a1.images
import com.example.appmodulo_a1.images1
import com.example.appmodulo_a1.images2
import com.example.appmodulo_a1.sampleProducts
import java.nio.file.WatchEvent

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
                    icon = {
                        Image(painter = painterResource(R.drawable.icon_catalogue), contentDescription = "Каталог")
//                           Icon(painter = painterResource(R.drawable.icon_catalogue), contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                           },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor =  MaterialTheme.colorScheme.primary,
                        indicatorColor  = Color.Transparent
                    )
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navCtrl.navigateTo(Screens.CartScreen) },
                    label = { Text("Корзина") },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Корзина") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor =  MaterialTheme.colorScheme.primary,
                        selectedTextColor =  MaterialTheme.colorScheme.primary,
                    )

                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navCtrl.navigateTo(Screens.ProfileScreen) },
                    label = { Text("Профиль") },
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Профиль") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor =  MaterialTheme.colorScheme.primary,
                        selectedTextColor =  MaterialTheme.colorScheme.primary
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

//            LazyVerticalStaggeredGrid(
//                columns = StaggeredGridCells.Fixed(2),
//                modifier = Modifier.fillMaxSize()
//                    .padding(horizontal = 10.dp),
//                horizontalArrangement = Arrangement.spacedBy(12.dp),
//                verticalItemSpacing = 12.dp
//            ) {
//                items(
//                    items = sampleProducts,
//                    key = { it.id }
//                ) { product ->
//                    ProductCard(
//                        product,
//                        onClick = { onProductClick(product) }
//                    )
//                }
//            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
//                verticalItemSpacing = 12.dp
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
        Surface(
            modifier = Modifier .heightIn(min = 200.dp, max = 200.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            ImageSlider(product.image)
        }

        Column(modifier = Modifier.padding(10.dp)) {
//            Surface(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(100.dp),
//                color = Color.LightGray,
//            ) {}

//            Spacer(modifier = Modifier.height(4.dp))

            Text(product.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(product.description, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        }
    }
}


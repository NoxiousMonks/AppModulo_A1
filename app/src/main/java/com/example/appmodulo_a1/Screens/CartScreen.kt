package com.example.appmodulo_a1.Screens

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.appmodulo_a1.Navigation.NavigationController
import com.example.appmodulo_a1.Product

data class CartItem(
    val product: Product,
    var quantity: Int = 1
) {
    var quantityState by mutableStateOf(quantity)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    onGoToCatalog: () -> Unit,
    onBack: () -> Unit,
    navCtrl: NavigationController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Корзина",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                        )
                },
                actions = {
                    IconButton(onClick = {navCtrl.clearCart()}) {
                        Icon(Icons.Default.Delete, contentDescription = "Clear cart")
                    }
                }
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
                    icon = { Icon(Icons.Default.Home, contentDescription = "Каталог") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { },
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
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF2F1EF))
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if(navCtrl.cartItems.isEmpty()){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        "В вашей корзине \n" +
                                "      пока пусто",
//                    "В вашей корзине",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineMedium
                    )
//                Text(
//                    "пока пусто",
//                    fontWeight = FontWeight.Bold,
//                    style = MaterialTheme.typography.headlineMedium
//                )

                    Text("Добавьте товары из каталога", color = Color.Gray)

                    Button(onClick = onGoToCatalog) {
                        Text("Перейти к каталогу")
                    }
                }
            } else {
                // 🔹 Список
                Column(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp)

                    ) {
                        items(navCtrl.cartItems) { item ->

                            Card (
                                modifier = Modifier.padding(vertical = 6.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ){
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {

                                    Box(
                                        modifier = Modifier
                                            .size(60.dp)
                                            .background(Color.Gray)
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(item.product.name)
                                        Text(item.product.price)
                                    }

                                    Row(
                                        modifier = Modifier.alignByBaseline(),
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                        ) {
                                        Button(onClick = {
                                            navCtrl.decreaseQuantity(item.product)
                                        },
                                            colors = ButtonDefaults.buttonColors(Color.LightGray),
                                            ) {
                                            Text("-")
                                        }

                                        Text("${item.quantityState}шт")

                                        Button(onClick = {
                                            navCtrl.increaseQuantity(item.product)
                                        },
                                            colors = ButtonDefaults.buttonColors(Color.LightGray)
                                            ) {
                                            Text("+", color = Color.Magenta)
                                        }


                                    }
                                }
                            }

                        }
                    }

                    Card(
                        modifier = Modifier.height(100.dp).fillMaxWidth(),
                        shape = RectangleShape,
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){
                            Column(modifier = Modifier.padding(10.dp)){
                                Text("Вся сумма", color = Color.Gray)
                                Text("${navCtrl.getTotalPrice()} ₸", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineSmall)
                            }

                            Spacer(modifier = Modifier.weight(1f))

                            Button(
                                onClick = { },
                                shape = MaterialTheme.shapes.medium,
                                contentPadding = PaddingValues(16.dp)
//                                modifier = Modifier.width(50.dp)
                            ) {
                                Text("Оформить заказ")
                            }
                        }
                    }
                }
            }
        }
    }
}
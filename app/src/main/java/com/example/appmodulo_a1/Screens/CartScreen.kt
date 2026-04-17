package com.example.appmodulo_a1.Screens

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteOutline
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app1.models.MainViewModel
import com.example.appmodulo_a1.Navigation.NavigationController
import com.example.appmodulo_a1.Product
import com.example.appmodulo_a1.sampleProducts

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
    viewModel: MainViewModel,
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
                    IconButton(onClick = { viewModel.clearCart() }) {
                        Icon(Icons.Default.Delete, contentDescription = "Clear cart")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { }, enabled = false) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = null,
                            tint = Color.Transparent
                        )
                    }
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
                    selected = true,
                    onClick = { },
                    label = { Text("Корзина") },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Корзина") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor =  MaterialTheme.colorScheme.primary,
                        selectedTextColor =  MaterialTheme.colorScheme.primary,
                        indicatorColor  = Color.Transparent
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF2F1EF))
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if (viewModel.cartItems.isEmpty()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Text(
                        "В вашей корзине \n" +
                                "      пока пусто",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text("Добавьте товары из каталога", color = Color.Gray)

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = onGoToCatalog,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF6149FF)
                        ),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text("Перейти к каталогу")
                    }
                }
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp)
                    ) {
                        items(viewModel.cartItems) { item ->

                            Card(
                                modifier = Modifier.padding(vertical = 6.dp).height(80.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Box(
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clip(shape = MaterialTheme.shapes.medium)
                                            .background(Color.Gray)
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(item.product.name)
                                        Text(item.product.price, fontWeight = FontWeight.Bold)
                                    }

                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
//                                        bOX CLICKABLE
                                        Box(modifier = Modifier
                                            .clickable(onClick = {
                                                viewModel.decreaseQuantity(item.product)
                                            })
                                            .size(32.dp)
                                            .clip(shape = CircleShape)
                                            .background(Color(0xFFF3F3F3)),
                                            contentAlignment = Alignment.Center
                                        ){
                                            if (item.quantityState > 1){
                                                Text("-", color = Color.Black, fontSize = 24.sp)
                                            } else {
                                                Icon(
                                                    Icons.Default.Delete,
                                                    contentDescription = "Delete",
                                                    tint = Color.Red
                                                )
                                            }
                                        }

                                        Text("${item.quantityState}шт")

                                        Box(modifier = Modifier
                                            .clickable(onClick = {
                                                viewModel.increaseQuantity(item.product)
                                            })
                                            .size(32.dp)
                                            .clip(shape = CircleShape)
                                            .background(Color(0xFFF3F3F3)),
                                            contentAlignment = Alignment.Center
                                        ){
                                            Text("+", color = MaterialTheme.colorScheme.primary, fontSize = 24.sp)
                                        }


                                    }
                                }
                            }

                        }
                    }

                    Box(
                        modifier = Modifier
                            .height(70.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text("Вся сумма", color = Color.Gray)
                                Text(
                                    "${viewModel.totalPrice.value} ₸",
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            }

                            Spacer(modifier = Modifier.weight(1f))

                            Button(
                                modifier = Modifier.width(200.dp),
                                onClick = { },
                                shape = MaterialTheme.shapes.medium,
                                contentPadding = PaddingValues(14.dp)
//                                modifier = Modifier.width(50.dp)
                            ) {
                                Text("Оформить заказ")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
        }
    }
}
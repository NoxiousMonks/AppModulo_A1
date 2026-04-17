package com.example.appmodulo_a1.Screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app1.models.MainViewModel
import com.example.appmodulo_a1.CartItem
import com.example.appmodulo_a1.ImageSlider
import com.example.appmodulo_a1.Product
//import com.example.appmodulo_a1.images

@Composable
fun DetailScreen(
    product: Product,
    onAddToCart: () -> Unit,
    onBack: () -> Unit,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize().verticalScroll(state = rememberScrollState())
            .background(Color(0xFFF2F1EF))
            .padding(),
//                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box() {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp),
            ) {
                ImageSlider(product.image)
            }

            OutlinedIconButton(
                onClick = onBack,
                shape = CircleShape,
                colors = IconButtonDefaults.outlinedIconButtonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.White
                ),
                border = IconButtonDefaults.outlinedIconButtonBorder(enabled = false)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
            }
        }

        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            Text(
                product.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                product.price,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyLarge
            )

            Column(modifier = Modifier.padding(12.dp)) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            "Описание:", fontWeight = FontWeight.Medium,
                        )

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            product.description,
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(paddingValues = PaddingValues(10.dp))
        ) {
            Button(
                onClick = onAddToCart,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF6149FF)
//                )
            ) {

                val productExists = viewModel.cartItems.find { it.product.id == product.id }

                if (productExists != null) {
                    Text("В корзине")
                } else {
                    Text("Добавить в корзину")
                }


            }
        }
    }
}

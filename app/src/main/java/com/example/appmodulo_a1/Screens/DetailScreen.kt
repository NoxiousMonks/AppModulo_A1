package com.example.appmodulo_a1.Screens

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appmodulo_a1.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    product: Product,
    onAddToCart: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F1EF))
            .padding(),
//                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(){
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                color = Color.Gray,
            ) {}

            OutlinedIconButton(onClick = onBack, shape = CircleShape,
                colors = IconButtonDefaults.outlinedIconButtonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.White
                ),
                border = IconButtonDefaults.outlinedIconButtonBorder(enabled = false)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
            }
        }

        Column(modifier = Modifier.padding(all = 10.dp)){
            Text(product.name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineMedium)

            Text(
                product.price,
                color = Color.Blue
            )

            Column(modifier = Modifier.padding(12.dp)){
                Text("Описание:", fontWeight = FontWeight.Medium)

                Spacer(modifier = Modifier.height(6.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                    Text(
                        product.description,
                        modifier = Modifier.padding(12.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onAddToCart,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            ) {
                Text("Добавить в корзину")

            }
        }
    }
}

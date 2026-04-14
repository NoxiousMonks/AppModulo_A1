package com.example.appmodulo_a1.Screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appmodulo_a1.EmailValidator
import com.example.appmodulo_a1.User
import kotlin.math.log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
//    data: User,
    onLogin: () -> Unit,
    onGoToRegister: () -> Unit,
    login: (String, String) -> Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Авторизация",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                    )
                },
                modifier = Modifier.background(Color.White),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { padding ->

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var errorEmail by remember { mutableStateOf(false) }
    var errorTextVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color(0xFFE5E5EA))
//            .padding(24.dp)
        ,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
//                .padding(16.dp)
            ,
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column() {
                OutlinedTextField(
                    value = email,
                    onValueChange = { newEmail ->
                        email = newEmail

                        // почта тексеру
                        errorEmail = newEmail.isNotEmpty() && !EmailValidator.isValidEmail(newEmail)
                    },
                    label = { Text("Почта") },
                    modifier = Modifier.fillMaxWidth(),

                    isError = errorEmail,

                    supportingText = {
                        if (errorEmail) {
                            Text("Введите корректную почту")
                        }
                    }
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Пароль") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )


            }
        }



        Column(modifier = Modifier.padding(horizontal = 16.dp)){

            if (errorTextVisibility) {
                Text("Заполните все поля", color = Color.Red)
            }


            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty() && login(email, password))
                        onLogin()
                    else {
                        errorTextVisibility = true
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small,
                contentPadding = PaddingValues(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6149FF)
                )
            ) {
                Text("Войти")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onGoToRegister,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small,
                contentPadding = PaddingValues(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6149FF)
                )
            ) {
                Text("Зарегистрироваться")
            }
        }
    }
}
}
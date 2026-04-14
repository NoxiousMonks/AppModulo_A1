package com.example.appmodulo_a1.Screens


import androidx.annotation.ColorInt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.appmodulo_a1.EmailValidator
import com.example.appmodulo_a1.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
//    initialData: User,
    onRegister: (User) -> Unit,
    onBack: () -> Unit
) {
    var login by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var errorEmail by remember { mutableStateOf(false) }
    var errorTextVisibility by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(
                "Регистрация",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            ) }, navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                }
            },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFE5E5EA)),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                OutlinedTextField(
                    value = login,
                    onValueChange = { login = it },
                    label = { Text("Логин") },
                    modifier = Modifier.fillMaxWidth()
                )

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

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = passwordVisible, onCheckedChange = { passwordVisible = it })
                    Text("Показать пароль")
                }
            }
            if (errorTextVisibility) {
                Text("Заполните все поля!", color = Color.Red)
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(modifier = Modifier.padding(all = 16.dp)){
                Button(
                    onClick = {
                        if (!errorEmail && email.isNotEmpty() && login.isNotEmpty() && password.isNotEmpty()) {

                            onRegister(User(login, email, password))
                        } else {
                            errorTextVisibility = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    contentPadding = PaddingValues(vertical = 16.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color(0xFF6149FF)
//                    )
                ) {
                    Text("Создать аккаунт")
                }
            }

        }
    }
}


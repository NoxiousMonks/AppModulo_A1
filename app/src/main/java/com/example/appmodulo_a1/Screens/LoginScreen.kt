package com.example.appmodulo_a1.Screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.appmodulo_a1.EmailValidator
import com.example.appmodulo_a1.User
import kotlin.math.log

@Composable
fun LoginScreen(
//    data: User,
    onLogin: () -> Unit,
    onGoToRegister: () -> Unit,
    login: (String, String) -> Boolean
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var errorEmail by remember { mutableStateOf(false) }
    var errorTextVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Авторизация", style = MaterialTheme.typography.headlineMedium)

        //Сактау тексеру
//        Text("Saved: ${data.email} ${data.password}")
//        Text("Input: $email $password")

        Spacer(modifier = Modifier.height(32.dp))

//        OutlinedTextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text("Email") },
//            modifier = Modifier.fillMaxWidth()
//        )

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

//        Spacer(modifier = Modifier.height(12.dp))

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

        if (errorTextVisibility) {
//            Text("Неверные данные для авторизация!", color = Color.Red)
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
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Войти")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = onGoToRegister,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Зарегистрироваться")
        }
    }
}
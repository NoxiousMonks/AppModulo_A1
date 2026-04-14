package com.example.appmodulo_a1.Screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appmodulo_a1.EmailValidator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
//    data: User,
    navigateToLogin: () -> Unit,
    navigateToRegister: () -> Unit,
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

        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }

        var errorEmail by rememberSaveable { mutableStateOf(false) }
        var errorTextVisibility by rememberSaveable { mutableStateOf(false) }

        var error = if (errorTextVisibility) "Заполните все поля"
        else if(errorEmail) "Введите корректную почту"
        else ""

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF3F3F3))
//            .padding(24.dp)
            ,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Column(modifier = Modifier.height(170.dp)) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { newEmail ->
                            email = newEmail

                            // почта тексеру
//                        errorEmail = newEmail.isNotEmpty() && !EmailValidator.isValidEmail(newEmail)
                        },
                        label = { Text("Почта") },
                        modifier = Modifier.fillMaxWidth(),

                        isError = errorEmail,

//                    supportingText = {
//                        if (errorEmail) {
//                            Text("Введите корректную почту")
//                        }
//                    }
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Пароль") },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (email.isEmpty() && password.isEmpty()) {
                        errorTextVisibility = true
                    } else {
                        errorTextVisibility = false
                    }

                    if (email.isNotEmpty() && !EmailValidator.isValidEmail(email)) {
                        errorEmail = true
                    } else {
                        errorEmail = false
                    }


                    Text(error, color = Color.Red)


                }
            }



            Column(modifier = Modifier.padding(horizontal = 16.dp)) {

//            if (errorTextVisibility) {
//                Text("Заполните все поля", color = Color.Red)
//            }


                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (email.isNotEmpty() && password.isNotEmpty() && login(email, password))
                            navigateToLogin()
//                    else {
//                        errorTextVisibility = true
//                    }
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
                    onClick = navigateToRegister,
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
package com.example.appmodulo_a1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.appmodulea1.ui.theme.AppModuleA1Theme
import androidx.compose.material3.Surface
import com.example.appmodulo_a1.Navigation.NavHub

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppModuleA1Theme() {
                Surface {
                    NavHub()
                }
            }
        }
    }
}



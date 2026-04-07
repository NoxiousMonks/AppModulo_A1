package com.example.appmodulo_a1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.appmodulea1.ui.theme.AppModuleA1Theme
import androidx.compose.material3.Surface
import com.example.app1.models.MainViewModel
import com.example.appmodulo_a1.Navigation.NavHub
import com.example.appmodulo_a1.Navigation.NavigationController

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val navigationController = NavigationController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppModuleA1Theme() {
                Surface {
                    NavHub(viewModel, navigationController)
                }
            }
        }
    }
}



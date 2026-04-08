package com.example.app1.models

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.appmodulo_a1.User
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val PREFS_NAME = "user_prefs"
    private val KEY_DATA = "user_data"

    private val sharedPreferences = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()

    var userData by mutableStateOf(User())

    fun login(email:String, pass:String): Boolean {
        return email.contentEquals(userData.email) && pass.contentEquals(userData.password)
    }


    init {
        loadData()
    }

    fun loadData(): User {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                val string = sharedPreferences.getString(KEY_DATA, null)
                if (string != null) {
                    try {
                        gson.fromJson(string, User::class.java)
                    } catch (e: Exception) {
                        User()
                    }
                } else {
                    User()
                }
            }
            userData = result
        }
        return userData
    }

    fun saveData(data: User) {
        userData = data
        viewModelScope.launch(Dispatchers.IO) {
            val json = gson.toJson(data)
            sharedPreferences.edit().putString(KEY_DATA, json).apply()
        }
    }
}
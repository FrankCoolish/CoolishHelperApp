package com.example.coolishhelperapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.coolishhelperapp.ui.screens.grocerylist.GroceryListScreen
import com.example.coolishhelperapp.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
             AppTheme  {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    GroceryApp()
                }
            }
        }
    }
}


package com.example.coolishhelperapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coolishhelperapp.ui.navigation.GroceryNavHost



/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun GroceryApp(navController: NavHostController = rememberNavController()){
    GroceryNavHost(navController = navController)
}
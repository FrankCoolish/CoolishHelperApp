package com.example.coolishhelperapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.coolishhelperapp.ui.screens.groceryedit.GroceryEditDestination
import com.example.coolishhelperapp.ui.screens.groceryedit.GroceryEditScreen
import com.example.coolishhelperapp.ui.screens.grocerylist.GroceryListDestination
import com.example.coolishhelperapp.ui.screens.grocerylist.GroceryListScreen


/**
 * Provides Navigation graph for the application.
 */
@Composable
fun GroceryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = GroceryListDestination.route,
        modifier = Modifier
    ) {
        composable(route = GroceryListDestination.route ) {
            GroceryListScreen(
                navigateToGroceryTaskEdit = {
                    navController.navigate("${GroceryEditDestination.route}/${it}")
                },
                modifier = Modifier
            )
        }
        composable(
            route = GroceryEditDestination.routeWithArgs,
            arguments = listOf(navArgument(GroceryEditDestination.itemIdArg){
                type = NavType.IntType
            })
        ) {
            GroceryEditScreen(
                navigateBack = {navController.popBackStack()}
            )
        }
    }
}
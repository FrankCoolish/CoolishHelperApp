package com.example.coolishhelperapp.ui

import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import com.example.coolishhelperapp.ui.screens.grocerylist.GroceryListViewModel
import com.example.coolishhelperapp.GroceryApplication
import com.example.coolishhelperapp.ui.screens.groceryedit.GroceryEditViewModel


/**
 * Provides Factory to create instance of ViewModel for the app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for GroceryListViewModel
        initializer {
            GroceryListViewModel(groceryApplication().container.groceriesRepository)
        }
        // Initializer for GroceryEditViewModel
        initializer {
            GroceryEditViewModel(
                this.createSavedStateHandle(),
                groceryApplication().container.groceriesRepository
            )
        }
    }
}

fun CreationExtras.groceryApplication(): GroceryApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as GroceryApplication)
package com.example.coolishhelperapp.ui

import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.example.coolishhelperapp.ui.grocerylist.GroceryListViewModel
import com.example.coolishhelperapp.GroceryApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            GroceryListViewModel(groceryApplication().container.groceriesRepository)
        }
    }
}

fun CreationExtras.groceryApplication(): GroceryApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as GroceryApplication)
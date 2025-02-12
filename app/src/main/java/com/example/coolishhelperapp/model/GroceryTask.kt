package com.example.coolishhelperapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class GroceryTask(
    val id: Int,
    val name: String,
    val quantity: Int,
    val initialChecked: Boolean = false
) {
    var checked: Boolean by mutableStateOf(initialChecked)
}

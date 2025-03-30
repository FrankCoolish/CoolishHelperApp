package com.example.coolishhelperapp.data.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "groceries")
data class GroceryTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val quantity: Int,
    var checked: Boolean = false,
   // val initialChecked: Boolean = false
) /*{
    var checked: Boolean by mutableStateOf(initialChecked)
}*/

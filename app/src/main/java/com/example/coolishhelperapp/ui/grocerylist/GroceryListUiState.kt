package com.example.coolishhelperapp.ui.grocerylist

import com.example.coolishhelperapp.model.FilterType

data class GroceryListUiState(
    val filter: FilterType = FilterType.SHOW_ALL
)
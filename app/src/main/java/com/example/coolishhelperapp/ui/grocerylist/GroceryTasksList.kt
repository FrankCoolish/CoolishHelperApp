package com.example.coolishhelperapp.ui.grocerylist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.coolishhelperapp.R
import com.example.coolishhelperapp.model.FilterType
import com.example.coolishhelperapp.model.GroceryTask

/**
 * This class defines the List of Tasks as Composable. It also holds the logic for the filters.
 * TODO: fin another place for filter logic
 */

@Composable
fun GroceryTasksList(
    groceryList: List<GroceryTask>,
    filter: FilterType,
    paddingValues: PaddingValues,
    onCheckedTask: (GroceryTask, Boolean) -> Unit,
    onDelete: (GroceryTask) -> Unit,
    modifier: Modifier = Modifier
) {
    val filteredList = when (filter) {
        FilterType.SHOW_ALL -> groceryList
        FilterType.SHOW_DONE -> groceryList.filter{ item -> item.checked}
        FilterType.SHOW_TODO -> groceryList.filter { item -> !item.checked}
    }
    LazyColumn(
        modifier = modifier,
        contentPadding = paddingValues
    ) { items(
        items = filteredList,
        key = { task -> task.id }
        ) { item ->
            GroceryItemCard(
                task = item,
                onCheckedTask = onCheckedTask,
                onDelete = onDelete,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}


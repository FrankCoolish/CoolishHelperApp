package com.example.coolishhelperapp.ui.screens.grocerylist

import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coolishhelperapp.R
import com.example.coolishhelperapp.data.model.FilterType
import com.example.coolishhelperapp.ui.AppViewModelProvider
import com.example.coolishhelperapp.ui.navigation.NavigationDestination
import com.example.coolishhelperapp.ui.screens.components.GroceryItem
import com.example.coolishhelperapp.ui.screens.components.GroceryListTopAppBar
import com.example.coolishhelperapp.ui.screens.components.GroceryListButtonBar

object GroceryListDestination: NavigationDestination {
    override val route =  "groceryList"
    override val titleRes = R.string.grocery_list
}

/**
 * Organizes the MainScreen with a topbar and bottombar. Basically it puts all together
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroceryListScreen(
    navigateToGroceryTaskEdit: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val viewModel: GroceryListViewModel =  viewModel(factory = AppViewModelProvider.Factory)
    val state by viewModel.state.collectAsState()

    Scaffold (
        topBar = {
            Column(modifier = modifier) {
                GroceryListTopAppBar()
                GroceryListButtonBar(
                    activeFilter = state.filter,
                    changeFilterToAll = { viewModel.changeFilterState(FilterType.SHOW_ALL) },
                    changeFilterToDone = {viewModel.changeFilterState(FilterType.SHOW_DONE) },
                    changeFilterToToDo = { viewModel.changeFilterState(FilterType.SHOW_TODO) }
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp, 15.dp,0.dp, 0.dp)),

            ) {
                Text(stringResource(R.string.bottomAppBar))
            }
        },
        floatingActionButton = {
            FloatingActionButtonAddTask(
                onClick = { viewModel.addBlankGroceryItem() }
            )
        }, floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        LazyColumn(
            reverseLayout = true,
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
        ) { items(
            items = viewModel.filterGroceryList(state.filter,state.groceriesList),
            key = { task -> task.id }
            ) { item ->
                GroceryItem(
                    task = item,
                    onGroceryTaskClick = navigateToGroceryTaskEdit,
                    onChecked =  { viewModel.updateGroceryTaskStatus(it, item.id) },
                    onDelete = {
                            viewModel.deleteGroceryItem(it)
                    },
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }

    }
}

@Composable
fun FloatingActionButtonAddTask(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onClick() },
        shape = CircleShape
    ) {
        Icon(Icons.Filled.Add, "Floating action button")
    }
}

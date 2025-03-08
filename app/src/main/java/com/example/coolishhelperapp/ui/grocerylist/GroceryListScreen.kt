package com.example.coolishhelperapp.ui.grocerylist

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coolishhelperapp.R
import com.example.coolishhelperapp.model.FilterType
import com.example.coolishhelperapp.model.GroceryTask
import com.example.coolishhelperapp.ui.theme.AppTheme

/**
 * Organizes the MainScreen with a topbar and bottombar. Basically it puts all together
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroceryListScreen(
    modifier: Modifier = Modifier,
    groceryListViewModel: GroceryListViewModel = viewModel()
)
{   val listUiState by groceryListViewModel.uiState.collectAsState()
    val task = GroceryTask(15,"newEntry",3,false)

    Scaffold (
        topBar = {
            Column(modifier = modifier) {
                CoolishHelperAppBar()
                GroceryListButtonBar(
                    activeFilter = listUiState.filter,
                    changeFilterToAll = { groceryListViewModel.changeGroceryFilter(FilterType.SHOW_ALL) },
                    changeFilterToDone = { groceryListViewModel.changeGroceryFilter(FilterType.SHOW_DONE) },
                    changeFilterToToDo = { groceryListViewModel.changeGroceryFilter(FilterType.SHOW_TODO) }
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp, 15.dp,0.dp, 0.dp)),

            ) {
                Text("BottomAppbarPlaceHolder")
            }
        },
        floatingActionButton = {
            FloatingActionButtonAddTask(
                onClick = {groceryListViewModel.addTask(task)}
            )
        }
    ) { innerPadding ->
        GroceryTasksList(
            groceryList = groceryListViewModel.tasks,
            filter = listUiState.filter,
            paddingValues = innerPadding,
            onCheckedTask = { task, checked ->
                groceryListViewModel.changeTaskChecked(task, checked)
            },
            onDelete = {task -> groceryListViewModel.removeTask(task)}
        )
    }
}


@ExperimentalMaterial3Api
@Composable
fun CoolishHelperAppBar(){
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.grocery_list),
                    style = MaterialTheme.typography.displayMedium,
                    //color = MaterialTheme.colorScheme.secondary
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@ExperimentalMaterial3Api
@Composable
fun GroceryListButtonBar(
    activeFilter:FilterType,
    changeFilterToAll: () -> Unit,
    changeFilterToDone: ()-> Unit,
    changeFilterToToDo: () -> Unit
){
    CenterAlignedTopAppBar(
        title = {
           Row(
               modifier = Modifier.padding(4.dp)
           ) {
                FilterButton(
                    isSelected = activeFilter == FilterType.SHOW_ALL,
                    label = stringResource( R.string.all ),
                    onClick = changeFilterToAll,

                )
                Spacer(modifier = Modifier.weight(1f))
                FilterButton(
                    isSelected = activeFilter == FilterType.SHOW_DONE,
                    label = stringResource(R.string.done),
                    onClick =  changeFilterToDone,
                )
                Spacer(modifier = Modifier.padding(8.dp))
                FilterButton(
                    isSelected = activeFilter == FilterType.SHOW_TODO,
                    label = stringResource(R.string.toDo),
                    onClick = changeFilterToToDo,
                )
            }
        }
    )
}

@Composable
fun FilterButton(
    label: String,
    isSelected: Boolean,
    onClick: ()-> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        colors = if (isSelected) ButtonDefaults.filledTonalButtonColors()
                else ButtonDefaults.outlinedButtonColors()
    ) {
        Text(label)
        //Spacer(modifier = Modifier.padding(4.dp))
    }

}

@Composable
fun FloatingActionButtonAddTask(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        shape = CircleShape
    ) {
        Icon(Icons.Filled.Add, "Floating action button")
    }
}

/*@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showBackground = false
)*/
@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun GroceryItemCardPreview() {
    AppTheme {
        GroceryListScreen()
    }
}
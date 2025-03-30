package com.example.coolishhelperapp.ui.grocerylist.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.coolishhelperapp.R
import com.example.coolishhelperapp.data.model.FilterType

@ExperimentalMaterial3Api
@Composable
fun GroceryListAppBar(){
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
    activeFilter: FilterType,
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
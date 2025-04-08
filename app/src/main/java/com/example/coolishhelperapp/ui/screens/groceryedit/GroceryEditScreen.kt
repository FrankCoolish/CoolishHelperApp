package com.example.coolishhelperapp.ui.screens.groceryedit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coolishhelperapp.R
import com.example.coolishhelperapp.ui.AppViewModelProvider
import com.example.coolishhelperapp.ui.navigation.NavigationDestination
import com.example.coolishhelperapp.ui.screens.components.GroceryListTopAppBar
import com.example.coolishhelperapp.ui.theme.AppTheme
import kotlinx.coroutines.launch

object GroceryEditDestination: NavigationDestination {
    override val route = "edit"
    override val titleRes = R.string.edit_entry
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroceryEditScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    viewModel: GroceryEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    println("groceryTaskEdit: ${viewModel.groceryTaskUiState}")
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            GroceryListTopAppBar(
            )
        }
    ) { innerPadding ->
        GroceryEditBody(
            groceryUiState = viewModel.groceryTaskUiState,
            onGroceryValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveGroceryTask()
                    navigateBack()
                }
            },
            onAddClick = { viewModel.addQuantity() },
            onSubtractClick = { viewModel.removeQuantity() },
            contentPadding = innerPadding,
            modifier = Modifier

        )

    }
}

@Composable
fun GroceryEditBody(
    groceryUiState: GroceryTaskUiState,
    onGroceryValueChange: (GroceryTaskDetails) -> Unit,
    onSaveClick: () -> Unit,
    onAddClick: () -> Unit,
    onSubtractClick: () -> Unit,
    contentPadding :PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = Modifier.padding(contentPadding)
    ) {
        GroceryInputForm(
            groceryDetails = groceryUiState.groceryTaskDetails,
            onValuesChange = onGroceryValueChange,
            onAddClick = { onAddClick() },
            onSubtractClick = { onSubtractClick() }
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onSaveClick,
                shape = MaterialTheme.shapes.small,
            ) { Icon(Icons.Rounded.Done, "save") }
        }
    }
}

@Composable
fun GroceryInputForm(
    groceryDetails: GroceryTaskDetails,
    onValuesChange: (GroceryTaskDetails) -> Unit = {},
    onAddClick: () -> Unit,
    onSubtractClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_large)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        OutlinedTextField(
            value = groceryDetails.name,
            onValueChange = { onValuesChange(groceryDetails.copy(name = it)) },
            label = { Text(stringResource(R.string.name)) },
            modifier = Modifier.fillMaxWidth()
        )
        Row (
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = groceryDetails.quantity,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(stringResource(R.string.quantity_label)) },
                onValueChange = { onValuesChange(groceryDetails.copy(quantity = it))},
                modifier = Modifier.weight(1f)
            )
            ElevatedButton(
                onClick = { onAddClick() }
            ) { Icon(painterResource(R.drawable.add), "add quantity") }
            ElevatedButton(
                onClick = { onSubtractClick()}
            ) { Icon(painterResource(R.drawable.remove), "remove quantity") }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GroceryEditScreenPreview() {
    AppTheme {
        GroceryEditScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun GroceryEditBodyPreview() {
    AppTheme {
        GroceryEditBody(
            groceryUiState = GroceryTaskUiState(
                GroceryTaskDetails(
                    name = "Brot", quantity = "1"
                )
            ),
            onGroceryValueChange = {},
            onSaveClick = {},
            onAddClick = {},
            onSubtractClick = {},
            contentPadding = PaddingValues(0.dp)
        )
    }
}


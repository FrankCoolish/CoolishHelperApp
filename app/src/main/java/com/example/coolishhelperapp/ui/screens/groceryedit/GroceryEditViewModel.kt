package com.example.coolishhelperapp.ui.screens.groceryedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coolishhelperapp.data.GroceriesRepository
import com.example.coolishhelperapp.data.model.GroceryTask
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


/**
 * ViewModel to retrieve and update an item from the [GroceriesRepository]'s data source.
 */
class GroceryEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val groceriesRepository: GroceriesRepository
) : ViewModel() {

    var groceryTaskUiState by mutableStateOf(GroceryTaskUiState())
        private set

    private val groceryTaskId: Int = 0 /*checkNotNull(savedStateHandle[GroceryEditDestination.groceryIdArg])*/

    init {
        viewModelScope.launch {
            groceryTaskUiState = groceriesRepository.getGroceryTaskStream(groceryTaskId)
                .filterNotNull()
                .first()
                .toGroceryTaskUiState()
        }
    }
}



/**
 * Represents Ui State for a grocery.
 */
data class GroceryTaskUiState(
    val groceryTaskDetails: GroceryTaskDetails = GroceryTaskDetails(),
    val isEntryValid: Boolean = false
)

data class GroceryTaskDetails(
    val id: Int = 0,
    val name: String = "",
    val quantity: String = "",
    val checked: Boolean = false,
)


/**
 * Extension function to convert [GroceryTask] to [GroceryTaskUiState]
 */
fun GroceryTask.toGroceryTaskUiState(isEntryValid: Boolean = false): GroceryTaskUiState = GroceryTaskUiState(
    groceryTaskDetails = this.toGroceryTaskDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [GroceryTask] to [GroceryTaskDetails]
 */
fun GroceryTask.toGroceryTaskDetails(): GroceryTaskDetails = GroceryTaskDetails(
    id = id,
    name  = name,
    quantity = quantity.toString(),
    checked = checked
)

/**
 * Extension function to convert [GroceryTaskDetails] to [GroceryTask]. If the value of [GroceryTaskDetails.quantity] is
 * not a valid [Int], then the quantity will be set to 0.
 */
fun GroceryTaskDetails.toGroceryTask(): GroceryTask = GroceryTask(
    id = id,
    name = name,
    quantity = quantity.toIntOrNull() ?: 0,
    checked = checked
)
package com.example.coolishhelperapp.ui.grocerylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coolishhelperapp.data.GroceriesRepository
import com.example.coolishhelperapp.data.model.FilterType
import com.example.coolishhelperapp.data.model.GroceryTask
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
* ViewModel to retrieve all groceries in the Room database.
*/
class GroceryListViewModel(
    private val groceriesRepository: GroceriesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(GroceryListState())
    val state: StateFlow<GroceryListState>
        get() = _state

    init {
        viewModelScope.launch {
            groceriesRepository.getAllGroceriesStream()
                .collect { groceries ->
                    _state.value = GroceryListState(groceries)
                }

        }
    }

    fun addBlankGroceryItem() {
        viewModelScope.launch {
            groceriesRepository.insertGroceryTask(GroceryTask(0,"NewItem", 1, false))
        }
    }

    fun deleteGroceryItem(groceryTask: GroceryTask) {
        viewModelScope.launch {
            groceriesRepository.deleteGroceryTask(groceryTask)
        }
    }

    fun updateGroceryTaskStatus(selected: Boolean, id: Int) {
        viewModelScope.launch {
            groceriesRepository.updateGroceryTaskStatus(selected, id)
        }
    }

    fun updateGroceryItem(item: GroceryTask) {
        viewModelScope.launch {
            groceriesRepository.updateGroceryTask(item)
        }
    }
}

/**
 * Ui State for HomeScreen
 */
data class GroceryListState(
    val groceriesList : List<GroceryTask> = emptyList(),
    val filter: FilterType = FilterType.SHOW_ALL
)

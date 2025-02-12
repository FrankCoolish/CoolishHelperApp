package com.example.coolishhelperapp.ui.grocerylist

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.coolishhelperapp.data.DummyDataGroceryTasksList
import com.example.coolishhelperapp.model.FilterType
import com.example.coolishhelperapp.model.GroceryTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GroceryListViewModel : ViewModel() {

    //for now only used for filtering the List
    private val _uiState = MutableStateFlow(GroceryListUiState())
    val uiState: StateFlow<GroceryListUiState> = _uiState.asStateFlow()


    private val _tasks = getGroceryTasks().toMutableStateList()

    val tasks: List<GroceryTask>
        get() = _tasks


    //get data from datalayer (for now it is just a HardcodedList)
    private fun getGroceryTasks() = DummyDataGroceryTasksList.groceries

    fun changeTaskChecked(item: GroceryTask, checked: Boolean) {
        _tasks.find { it.id == item.id }
            ?.let { task -> task.checked = checked }
    }

    fun changeGroceryFilter(filter: FilterType) {
        _uiState.update { currentState ->
            currentState.copy(
                filter = filter
            )
        }
    }

    fun addTask(item: GroceryTask){
        _tasks.add(item)
    }

    fun removeTask(item: GroceryTask) {
        _tasks.remove(item)
    }

}
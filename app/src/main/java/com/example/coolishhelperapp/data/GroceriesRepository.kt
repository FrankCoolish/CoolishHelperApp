package com.example.coolishhelperapp.data

import com.example.coolishhelperapp.data.model.GroceryTask
import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [GroceryTask] from a given data source.
 */
interface GroceriesRepository {

    /**
     * Retrieve all the groceries from the given data source.
     */
    fun getAllGroceriesStream(): Flow<List<GroceryTask>>

    /**
     * Insert grocery in the data source
     */
    suspend fun insertGroceryTask(groceryTask: GroceryTask)

    /**
     * Delete grocery from the data source
     */
    suspend fun deleteGroceryTask(groceryTask: GroceryTask)

    /**
     * Update grocery in the data source
     */
    suspend fun updateGroceryTask(groceryTask: GroceryTask)


    /**
     * Update the status of the grocery in the data source
     */
    suspend fun updateGroceryTaskStatus(isComplete: Boolean, id: Int)

}
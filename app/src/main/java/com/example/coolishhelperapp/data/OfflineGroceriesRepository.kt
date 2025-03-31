package com.example.coolishhelperapp.data

import com.example.coolishhelperapp.data.local.GroceryDao
import com.example.coolishhelperapp.data.model.GroceryTask
import kotlinx.coroutines.flow.Flow

class OfflineGroceriesRepository(private val groceryDao: GroceryDao) : GroceriesRepository {

    override fun getAllGroceriesStream(): Flow<List<GroceryTask>>  = groceryDao.getAllGroceries()

    override suspend fun insertGroceryTask(groceryTask: GroceryTask) = groceryDao.insert(groceryTask)

    override suspend fun deleteGroceryTask(groceryTask: GroceryTask) = groceryDao.delete(groceryTask)

    override suspend fun updateGroceryTask(groceryTask: GroceryTask) = groceryDao.update(groceryTask)

    override suspend fun updateGroceryTaskStatus(isComplete: Boolean, id: Int)  = groceryDao.updateStatus(isComplete, id)

    override fun getGroceryTaskStream(id: Int): Flow<GroceryTask?> = groceryDao.getGrocery(id)

}
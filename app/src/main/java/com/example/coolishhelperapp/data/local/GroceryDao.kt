package com.example.coolishhelperapp.data.local

import androidx.room.*
import com.example.coolishhelperapp.data.model.GroceryTask
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(groceryTask: GroceryTask)

    @Update
    suspend fun update(groceryTask: GroceryTask)

    @Delete
    suspend fun delete(groceryTask: GroceryTask)

    @Query("SELECT * from groceries")
    fun getAllGroceries(): Flow<List<GroceryTask>>

    @Query("SELECT * from groceries WHERE id = :id")
    fun getGrocery(id: Int): Flow<GroceryTask>

    @Query("UPDATE groceries SET checked = :isComplete WHERE id = :id")
    suspend fun updateStatus(isComplete: Boolean, id: Int)
}
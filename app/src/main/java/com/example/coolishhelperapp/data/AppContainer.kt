package com.example.coolishhelperapp.data

import android.content.Context
import com.example.coolishhelperapp.data.local.GroceriesLocalDatabase


/**
 * App container for Dependency injection
 */
interface AppContainer {
    val groceriesRepository : GroceriesRepository
}


/**
 * [AppContainer] implementation that provides instance of [OfflineGroceriesRepository]
 */
class AppDataContainer(private val context: Context) :AppContainer {

    /**
     * Implementation for [GroceriesRepository]
     */
    override val groceriesRepository: GroceriesRepository by lazy {
        OfflineGroceriesRepository(GroceriesLocalDatabase.getDatabase(context).groceryDao())
    }

}

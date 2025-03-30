package com.example.coolishhelperapp.data.helper

import com.example.coolishhelperapp.data.model.GroceryTask

object DummyDataGroceryTasksList {
     var groceries = mutableListOf<GroceryTask>(
          GroceryTask(1, "Bread", 1, false),
          GroceryTask(2, "Cheese", 10, true),
          GroceryTask(3, "passierte Tomato", 2, false),
          GroceryTask(4, "Zu langer Eintrag what to do go on go on go on", 3, true),
          GroceryTask(5, "Wasser", 3, false),
          GroceryTask(6, "kurz W", 1, true)
     )
}
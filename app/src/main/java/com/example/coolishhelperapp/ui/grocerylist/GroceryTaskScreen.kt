package com.example.coolishhelperapp.ui.grocerylist

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coolishhelperapp.R
import com.example.coolishhelperapp.model.GroceryTask
import com.example.coolishhelperapp.ui.theme.AppTheme

@Composable
fun GroceryTaskScreen(
    task: GroceryTask,
    modifier: Modifier
) {
    TaskEntrys(
        task = task,
    )
}

@Composable
fun TaskEntrys(
    task:GroceryTask
) {
    Card(
        modifier = Modifier
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TaskFieldString(
                stringResource(R.string.name),
                task.name
            )
            TaskFieldQuantity(
                stringResource(R.string.quantity_label),
                task.quantity,
            )

        }
    }
}

@Composable
fun TaskFieldString(
    label: String,
    value: String
) {
    Row {
        Text("$label:")
        Spacer(Modifier.weight(1f))
        Text(value)
    }
}

@Composable
fun TaskFieldQuantity(
    label: String,
    value: Int,
) {
    Row {
        Text("$label:")
        Spacer(Modifier.weight(1f))
        Text(value.toString())
    }
}



@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun GroceryTaskScreenPreview(
) {
    AppTheme {
        var task = GroceryTask(1,"Brot",2,false)
        GroceryTaskScreen(
            task,
            modifier = Modifier
        )
    }
}
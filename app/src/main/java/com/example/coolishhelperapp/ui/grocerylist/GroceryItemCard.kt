package com.example.coolishhelperapp.ui.grocerylist

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue.EndToStart
import androidx.compose.material3.SwipeToDismissBoxValue.Settled
import androidx.compose.material3.SwipeToDismissBoxValue.StartToEnd
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.coolishhelperapp.model.GroceryTask
import com.example.coolishhelperapp.ui.theme.AppTheme


@Composable
fun GroceryItemCard(
    task: GroceryTask,
    onCheckedTask: (GroceryTask, Boolean) -> Unit,
    onDelete: (GroceryTask) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val currentItem by rememberUpdatedState(task)
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            when(it) {
                StartToEnd -> {
                    onDelete(currentItem)
                    Toast.makeText(context, "Eintrag gelöscht", Toast.LENGTH_SHORT).show()
                }
                EndToStart -> {
                    onDelete(currentItem)
                    Toast.makeText(context,"Eintrag gelöscht", Toast.LENGTH_SHORT).show()
                }
                Settled -> return@rememberSwipeToDismissBoxState false
            }
            return@rememberSwipeToDismissBoxState true
        },
        positionalThreshold = { it * .25f}
    )
    SwipeToDismissBox(
        state = dismissState,
        modifier = modifier,
        backgroundContent = { DismissBackground(dismissState) },
        content = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    //.padding(dimensionResource(R.dimen.padding_small)),
            ) {
                GroceryTaskItem(
                    taskName = task.name,
                    taskQuantity = task.quantity,
                    checked = task.checked,
                    onCheckedChange = { checked: Boolean -> onCheckedTask( task, checked) },
                )
            }
        }
    )
}

/*@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showBackground = false
)*/
@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun GroceryCardPreview(){
    AppTheme {
        var task = GroceryTask(1,"Brot",2,false)
        GroceryItemCard(
            task,
            onCheckedTask = {item, checked -> changeStatus(item, item.checked) },
            onDelete = {}
        )
    }
}

fun changeStatus(task: GroceryTask, status: Boolean ) {
    task.checked = !status
}
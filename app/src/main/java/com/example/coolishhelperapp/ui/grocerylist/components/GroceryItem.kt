package com.example.coolishhelperapp.ui.grocerylist.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coolishhelperapp.R
import com.example.coolishhelperapp.data.model.GroceryTask
import com.example.coolishhelperapp.ui.theme.AppTheme

@Composable
fun GroceryItem(
    task: GroceryTask,
    onChecked: (Boolean) -> Unit,
    onDelete: (GroceryTask) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = Modifier
        .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            Text(
                text = task.name,
                style = MaterialTheme.typography.headlineMedium,
                textDecoration = if (task.checked) TextDecoration.LineThrough else TextDecoration.None,
                modifier = Modifier.weight(3f)
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick =  { onDelete(task) } ,
            ) { Icon(Icons.Rounded.Delete, "delete")  }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.weight(1.5f)
            ) {
                Text(
                    text = stringResource(R.string.quantity, task.quantity),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Checkbox(
                    checked = task.checked,
                    onCheckedChange =  { onChecked(it) }
                )
            }
        }
    }
}


@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun GroceryItemPreview(){
    AppTheme {
        var task = GroceryTask(1,"Brot",2,false)
        GroceryItem(
            task,
            onChecked = {},
            onDelete = {},
            modifier = Modifier
        )
    }
}

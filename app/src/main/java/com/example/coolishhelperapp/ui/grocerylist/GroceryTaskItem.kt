package com.example.coolishhelperapp.ui.grocerylist

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.coolishhelperapp.R
import com.example.coolishhelperapp.model.GroceryTask
import com.example.coolishhelperapp.ui.theme.AppTheme

/**
 * Represents all the data of an item. Changes appearance to be editable.
 * TODO: split to different appearances (editable/ non-editable)
 */


@Composable
fun GroceryTaskItem(
    taskName: String,
    taskQuantity: Int,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    var quantity by remember { mutableStateOf(taskQuantity) }
    var text by remember { mutableStateOf(taskName) }
    var editable by rememberSaveable { mutableStateOf(false) }
    if(editable) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_small))
        ) { TextField(
                value = text,
                onValueChange = {text = it },
                modifier = Modifier.weight(3f)
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { editable = !editable },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Icon(Icons.Rounded.Done, "edit done")
            }
            Column(
                modifier = Modifier.weight(2.0f)
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(
                        onClick = {quantity++ },
                    ) {
                        Icon(painterResource(R.drawable.add), "more Quantity")
                    }
                    Text(
                        text = taskQuantity.toString(),
                        fontSize = 30.sp,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    IconButton(
                        onClick = {quantity--}
                    ) {
                        Icon(
                            painterResource(R.drawable.remove),
                            "less Quantity"
                        )
                    }
                }
            }
        }
    } else {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            Text(
                text = taskName,
                style = MaterialTheme.typography.headlineMedium,
                textDecoration = if (checked) TextDecoration.LineThrough else TextDecoration.None,
                modifier = Modifier.weight(3f)
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {editable = !editable},
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Icon(Icons.Rounded.Edit, "edit Task")
            }
            Column(
                modifier = Modifier.weight(1.5f)
            ) {
                Text(
                    text = stringResource(R.string.quantity, taskQuantity),
                    style = MaterialTheme.typography.bodyLarge,
                )
                Checkbox(
                    checked = checked,
                    onCheckedChange = onCheckedChange
                )
            }
        }
    }
}

@Preview
@Composable
fun GroceryTaskItemPreview() {
    AppTheme {
        val task = GroceryTask(1,"Brot",2,false)
        GroceryItemCard(
            task,
            onCheckedTask = {item, checked -> changeStatus(item, item.checked) },
            onDelete = {}
        )
    }
}
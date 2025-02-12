package com.example.coolishhelperapp.ui.grocerylist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import com.example.coolishhelperapp.R

@Composable
fun GroceryTaskItem(
    taskName: String,
    taskQuantity: Int,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = taskName,
            style = MaterialTheme.typography.headlineMedium,
            textDecoration = if(checked) TextDecoration.LineThrough else TextDecoration.None,
            modifier = Modifier.weight(4f)
        )
        Spacer(modifier = Modifier.weight(1f))
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
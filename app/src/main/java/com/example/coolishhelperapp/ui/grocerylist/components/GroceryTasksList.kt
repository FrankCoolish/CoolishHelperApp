package com.example.coolishhelperapp.ui.grocerylist.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coolishhelperapp.R
import com.example.coolishhelperapp.data.helper.DummyDataGroceryTasksList
import com.example.coolishhelperapp.data.model.FilterType
import com.example.coolishhelperapp.data.model.GroceryTask
import com.example.coolishhelperapp.ui.theme.AppTheme

/**
 * This class defines the List of Tasks as Composable. It also holds the logic for the filters.
 * TODO: fin another place for filter logic
 */

@Composable
fun GroceryTasksList(
    groceryList: List<GroceryTask>,
    filter: FilterType,
    paddingValues: PaddingValues,
    onChecked : (Boolean) -> Unit,
    onDelete: (GroceryTask) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_small)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) { items(
        items = groceryList,
        key = { task -> task.id }
        ) { item ->
            GroceryItem(
                task = item,
                onChecked = onChecked,
                onDelete = onDelete,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun GroceryTaskListPreview() {
    AppTheme {
        GroceryTasksList(
            groceryList = DummyDataGroceryTasksList.groceries,
            filter = FilterType.SHOW_ALL,
            paddingValues = PaddingValues(0.dp),
            onChecked = {},
            onDelete = {},
            modifier = Modifier,
        )
    }
}


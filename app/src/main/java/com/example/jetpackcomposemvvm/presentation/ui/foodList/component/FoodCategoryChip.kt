package com.example.jetpackcomposemvvm.presentation.ui.foodList.component


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.unit.dp

@Composable
fun FoodCategoryChip(
    category: String,
    isSelected: Boolean,
    onExecuteCategoryChange: (String) -> Unit,
    onExecuteSearch: () -> Unit,
) {
    Surface(
        modifier = Modifier.padding(end = 8.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if(isSelected) androidx.compose.ui.graphics.Color.Gray else MaterialTheme.colors.primary
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onExecuteCategoryChange(category)
                    onExecuteSearch()
                }
            )
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.body2,
                color = androidx.compose.ui.graphics.Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}



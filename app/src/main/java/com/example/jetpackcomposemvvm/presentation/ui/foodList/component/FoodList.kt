package com.example.jetpackcomposemvvm.presentation.ui.foodList.component

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.jetpackcomposemvvm.R
import com.example.jetpackcomposemvvm.domain.model.Food

@Composable
fun foodList(
    foods: List<Food>,
    progressVisibility: Boolean,
    mode: Boolean,
    navController: NavController

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.surface)
    ) {
        LazyColumn {
            itemsIndexed(items = foods) { index, item ->
                foodCard(food = item, onClick = {
                    if (item.id != null) {
                        var bundle = Bundle()
                        bundle.putInt("foodId", item.id)
                        bundle.putBoolean("mood", mode)
                        navController.navigate(R.id.goToDescription, bundle)

                    } else {
                        Log.d("Error", "id equal null")
                    }

                })
            }
        }
        // in the end be in the top in box
        CircularProgressBarIndicator(progressVisibility)

    }
}
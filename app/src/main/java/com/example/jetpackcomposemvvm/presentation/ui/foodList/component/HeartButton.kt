package com.example.jetpackcomposemvvm.presentation.ui.foodList.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposemvvm.R
import com.example.jetpackcomposemvvm.utlis.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi


enum class HeartButtonState {
    IDLE, ACTIVE
}

@ExperimentalCoroutinesApi
@Composable
 fun HeartButton(
    modifier: Modifier,
    state: MutableState<HeartButtonState>,
    onToggle: () -> Unit,
) {
    if (state.value == HeartButtonState.ACTIVE) {
        loadPicture(drawable = R.drawable.heart_red).value?.let { image ->
            Image(
                bitmap = image.asImageBitmap(),
                modifier = modifier
                    .clickable(
                        onClick = onToggle,
                    )
                    .width(50.dp)
                    .height(50.dp),
                contentDescription = ""
            )
        }
    } else {
        loadPicture(drawable = R.drawable.heart_grey).value?.let { image ->
            Image(
                bitmap = image.asImageBitmap(),
                modifier = modifier
                    .clickable(
                        onClick = onToggle,
                    )
                    .width(50.dp)
                    .height(50.dp),
                contentDescription = ""
            )
        }
    }
}

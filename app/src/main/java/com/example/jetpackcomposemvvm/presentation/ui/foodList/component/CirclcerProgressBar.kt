package com.example.jetpackcomposemvvm.presentation.ui.foodList.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun CircularProgressBarIndicator(
    isVisible: Boolean
) {
    if (isVisible) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.background)
        ) {
            val (progress, text) = createRefs()
            val guidLine = createGuidelineFromTop(0.3f)
            CircularProgressIndicator(
                modifier = Modifier.constrainAs(progress) {
                    top.linkTo(guidLine)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                color = MaterialTheme.colors.primary

            )
            Text(
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(progress.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                },
                text = "Loading..",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp
                )
            )


        }


    }
}
package com.example.jetpackcomposemvvm.utlis

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.jetpackcomposemvvm.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val DEFAULT_IMAGE = R.drawable.empty_plate
const val NIGHT = R.drawable.ic_night
const val LIGHT = R.drawable.ic_light

@SuppressLint("UnrememberedMutableState")
@ExperimentalCoroutinesApi
@Composable
fun loadImage(
    url: String,
    @DrawableRes defaultImage: Int
): MutableState<Bitmap?> {
    val bitMapState: MutableState<Bitmap?> = mutableStateOf(null)
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(defaultImage)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {}
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitMapState.value = resource
            }
        })

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {}
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitMapState.value = resource
            }
        })
    return bitMapState
}

@SuppressLint("UnrememberedMutableState")
@ExperimentalCoroutinesApi
@Composable
fun loadPicture(@DrawableRes drawable: Int): MutableState<Bitmap?> {

    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(drawable)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {}
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }
        })
    return bitmapState
}



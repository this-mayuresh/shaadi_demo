package com.shaadi.demo.utility

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.shaadi.demo.R
import com.shaadi.demo.data.cache.model.UserCache
import com.shaadi.demo.data.model.User


const val BASE_URL = "https://randomuser.me/api/"
const val GENERIC_NETWORK_ERROR = "Something went wrong"
fun String.getGender()= if(this == "female"){"F"}else{ "M"}


fun mapUserFromCache(entity: UserCache): User {
    return User(
        id = entity.id,
        name = entity.name,
        bio = entity.bio,
        status = entity.status,
        image = entity.image
    )
}


const val DEFAULT_RECIPE_IMAGE = R.drawable.ic_launcher_background


@SuppressLint("UnrememberedMutableState")
@Composable
fun loadPicture(url: String, @DrawableRes defaultImage: Int): MutableState<Bitmap?> {

    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    // show default image while image loads
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(defaultImage)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) { }
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }
        })

    // get network image
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) { }
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }
        })

    return bitmapState
}
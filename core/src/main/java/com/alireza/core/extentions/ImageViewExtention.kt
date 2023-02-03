package com.alireza.core.extentions

import android.widget.ImageView
import com.alireza.core.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

private const val ROUNDED_CORNERS_24 = 24

fun ImageView.loadImage(url: String, width:Int,height:Int){
    Glide.with(this)
        .load(url)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(ROUNDED_CORNERS_24)))
        .apply(RequestOptions().override(width, height))
        .error(R.drawable.ic_photo_load_error)
        .placeholder(R.drawable.ic_load_photo)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .skipMemoryCache(false)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE))
        .into(this)
}

fun ImageView.loadImage(url: String){
    Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .skipMemoryCache(false)
        .error(R.drawable.ic_photo_load_error)
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE))
        .into(this)
}
package com.alireza.core.extentions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

private const val ROUNDED_CORNERS_24 = 24

fun ImageView.loadImage(url: String){
    Glide.with(this)
        .load(url)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(ROUNDED_CORNERS_24)))
        .apply(RequestOptions().override(400, 400))
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .skipMemoryCache(false)
        .centerCrop()
        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE))
        .into(this)
}
package com.alireza.flickrfinder.presentation.router

import android.content.Context
import android.content.Intent
import android.os.Bundle

private fun createIntent(context: Context, action: String, extras: Bundle? = null) =
    Intent(action).setPackage(context.packageName).also { intent ->
        extras?.let { intent.putExtras(it) }
    }

fun flickrFinderIntent(context: Context) =
    createIntent(context, IntentFilter_FlickrFinder)
package com.alireza.picture.data.remote.api

import com.alireza.core.BuildConfig

internal const val RECENT_PHOTO_URL =
    "?method=flickr.photos.getRecent&format=json&nojsoncallback=1&per_page=25" +
            "&extras=owner_name%2Curl_s&api_key=${BuildConfig.API_KEY}"

internal const val SEARCH_PHOTO_URL =
    "?method=flickr.photos.search&format=json&nojsoncallback=1&per_page=25" +
            "&extras=owner_name%2Curl_s&api_key=${BuildConfig.API_KEY}"
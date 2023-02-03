package com.alireza.picture.data.remote.api

import com.alireza.core.BuildConfig

internal const val RECENT_PHOTO_URL =
    "?method=flickr.photos.getRecent&format=json&nojsoncallback=1&per_page=25" +
            "&extras=owner_name%2Curl_s%2Curl_l&api_key=${BuildConfig.API_KEY}"

internal const val SEARCH_PHOTO_URL =
    "?method=flickr.photos.search&format=json&nojsoncallback=1&per_page=25" +
            "&extras=owner_name%2Curl_s%2Curl_l&api_key=${BuildConfig.API_KEY}"


internal const val PHOTO_DETAILS_URL =
    "?method=flickr.photos.getInfo&photo_id=52664705167" +
            "&format=json&nojsoncallback=1&api_key=${BuildConfig.API_KEY}"

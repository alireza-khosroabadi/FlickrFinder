package com.alireza.flickrfinder.fakeData.fakeDataModel

import com.alireza.picture.data.remote.entity.recentPhoto.RecentPhotoResponse
import com.google.gson.Gson

internal val fakeRecentPhotoEntityResponse = Gson().fromJson(
    "{ \"photos\": { \"page\": 1, \"pages\": \"1000\", \"perpage\": 1, \"total\": \"1000\", \n" +
            "    \"photo\": [\n" +
            "      { \"id\": \"52662561938\", \"owner\": \"197527388@N05\", \"secret\": \"091c42c048\", \"server\": \"65535\", \"farm\": 66, \"title\": \"20171021_215154\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0, \"url_s\": \"https:\\/\\/live.staticflickr.com\\/65535\\/52662561938_091c42c048_m.jpg\", \"height_s\": \"180\", \"width_s\": \"240\" }\n" +
            "    ] }, \"stat\": \"ok\" }",
    RecentPhotoResponse::class.java
)

package com.alireza.picture.data.fakeData

import com.alireza.picture.data.remote.entity.recentPhoto.RecentPhotoResponse
import com.google.gson.Gson

internal val fakeRecentPhotoEntityResponse = Gson().fromJson(
    "{ \"photos\": { \"page\": 1, \"pages\": \"1000\", \"perpage\": 1, \"total\": \"1000\", \n" +
            "    \"photo\": [\n" +
            "      { \"id\": \"52663009941\", \"owner\": \"151775840@N03\", \"secret\": \"d122e7b037\", \"server\": \"65535\", \"farm\": 66, \"title\": \"01.02.2023 - Reunião com Cooperbarco - foto Pedro Perez\\/PMF\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0, \"ownername\": \"Prefeitura Florianópolis\", \"url_s\": \"https:\\/\\/live.staticflickr.com\\/65535\\/52663009941_d122e7b037_m.jpg\", \"height_s\": \"160\", \"width_s\": \"240\" }\n" +
            "    ] }, \"stat\": \"ok\" }",
    RecentPhotoResponse::class.java
)

val fakeRecentPhotoFailedFlow = RecentPhotoResponse(state = "fail", code = 1, message = "fail to fetch data")
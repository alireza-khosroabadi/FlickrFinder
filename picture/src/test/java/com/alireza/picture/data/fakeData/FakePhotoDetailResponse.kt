package com.alireza.picture.data.fakeData

import com.alireza.picture.data.remote.entity.photoDetail.PhotoDetailResponse
import com.google.gson.Gson

internal val fakePhotoDetailResponse = Gson().fromJson(
    "{ \"photo\": { \"id\": \"52667395057\", \"secret\": \"037caed2ae\", \"server\": \"65535\", \"farm\": 66, \"dateuploaded\": \"1675500029\", \"isfavorite\": 0, \"license\": 0, \"safety_level\": 0, \"rotation\": 0, \"originalsecret\": \"f2fdba00e2\", \"originalformat\": \"jpg\", \n" +
            "    \"owner\": { \"nsid\": \"46221820@N02\", \"username\": \"beswickl\", \"realname\": \"Laura Beswick\", \"location\": \"\", \"iconserver\": \"4011\", \"iconfarm\": 5, \"path_alias\": \"\", \n" +
            "      \"gift\": { \"gift_eligible\": \"1\", \n" +
            "        \"eligible_durations\": [\n" +
            "          \"year\",\n" +
            "          \"month\",\n" +
            "          \"week\"\n" +
            "        ], \"new_flow\": \"1\" } }, \n" +
            "    \"title\": { \"_content\": \"Still Life\" }, \n" +
            "    \"description\": { \"_content\": \"\" }, \n" +
            "    \"visibility\": { \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 }, \n" +
            "    \"dates\": { \"posted\": \"1675500029\", \"taken\": \"2023-01-20 19:46:37\", \"takengranularity\": 0, \"takenunknown\": 0, \"lastupdate\": \"1675500030\" }, \"views\": 0, \n" +
            "    \"editability\": { \"cancomment\": 0, \"canaddmeta\": 0 }, \n" +
            "    \"publiceditability\": { \"cancomment\": 1, \"canaddmeta\": 0 }, \n" +
            "    \"usage\": { \"candownload\": 1, \"canblog\": 0, \"canprint\": 0, \"canshare\": 1 }, \n" +
            "    \"comments\": { \"_content\": 0 }, \n" +
            "    \"notes\": { \n" +
            "      \"note\": [\n" +
            "        \n" +
            "      ] }, \n" +
            "    \"people\": { \"haspeople\": 0 }, \n" +
            "    \"tags\": { \n" +
            "      \"tag\": [\n" +
            "        \n" +
            "      ] }, \n" +
            "    \"urls\": { \n" +
            "      \"url\": [\n" +
            "        { \"type\": \"photopage\", \"_content\": \"https:\\/\\/www.flickr.com\\/photos\\/46221820@N02\\/52667395057\\/\" }\n" +
            "      ] }, \"media\": \"photo\" }, \"stat\": \"ok\" }",
    PhotoDetailResponse::class.java
)
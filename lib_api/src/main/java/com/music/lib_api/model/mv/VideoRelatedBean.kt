package com.music.lib_api.model.mv

import com.music.lib_api.model.search.FeedSearchBean
import java.util.*

class VideoRelatedBean {
    var code = 0
    var message: String? = null
    var data: ArrayList<FeedSearchBean.ResultBean.VideosBean>? = null
}
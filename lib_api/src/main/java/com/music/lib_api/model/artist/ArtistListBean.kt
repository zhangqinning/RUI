package com.music.lib_api.model.artist

import com.music.lib_api.model.playlist.TopListDetailBean
import java.util.*

class ArtistListBean {
    var code = 0
    var isMore = false
    var artists: ArrayList<TopListDetailBean.Artist>? = null
}
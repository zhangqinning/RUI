package com.music.lib_api.model.dj

import com.music.lib_api.model.dj.DjSubListBean.DjRadios
import java.util.*

class DjTopListBean {
    var code = 0
    var updateTime: Long = 0
    var toplist: ArrayList<DjRadios>? = null
}
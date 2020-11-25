package com.music.lib_api.model.user

import com.music.lib_api.model.song.SongDetailBean
import java.util.*

class UserRecordBean {
    var code = 0
    var allData: ArrayList<UserRecord>? = null
    var weekData: ArrayList<UserRecord>? = null

    class UserRecord {
        var score = 0
        var playCount = 0
        var song: SongDetailBean.SongsBean? = null
    }
}
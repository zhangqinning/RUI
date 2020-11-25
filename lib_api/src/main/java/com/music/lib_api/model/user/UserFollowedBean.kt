package com.music.lib_api.model.user

import com.music.lib_api.model.search.UserSearchBean
import java.util.*

class UserFollowedBean {
    var code = 0
    var isMore = false
    var followeds: ArrayList<UserSearchBean.ResultBean.UserprofilesBean>? = null
}
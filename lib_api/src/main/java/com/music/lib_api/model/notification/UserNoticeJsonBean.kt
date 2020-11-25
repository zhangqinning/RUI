package com.music.lib_api.model.notification

import com.music.lib_api.model.search.SynthesisSearchBean.ResultBean.PlayListBean.PlayListsBean
import com.music.lib_api.model.search.UserSearchBean.ResultBean.UserprofilesBean
import com.music.lib_api.model.song.MusicCommentBean.CommentsBean

class UserNoticeJsonBean {
    var type = 0
    var user: UserprofilesBean? = null

    //评论的通知
    var comment: CommentsBean? = null

    //歌单的通知
    var playlist: PlayListsBean? = null
}
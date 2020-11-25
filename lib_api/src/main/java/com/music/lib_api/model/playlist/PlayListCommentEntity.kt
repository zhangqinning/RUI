package com.music.lib_api.model.playlist

import com.chad.library.adapter.base.entity.SectionEntity
import com.music.lib_api.model.song.MusicCommentBean

class PlayListCommentEntity : SectionEntity {


    override var isHeader: Boolean = false

    //评论数量
    var count = ""
        private set
    private var header: String? = null
    private var comment: MusicCommentBean.CommentsBean? = null

    //头布局数据  头布局标题 评论数量
    constructor(header: String?, count: String) {
        isHeader = true
        this.header = header
        this.count = count
    }

    constructor(header: String?) {
        isHeader = true
        this.header = header
    }

    //非头布局数据
    constructor(Comment: MusicCommentBean.CommentsBean?) {
        comment = Comment
    }

    fun getComment(): MusicCommentBean.CommentsBean? {
        return comment
    }

    fun getHeader(): String? {
        return header
    }


}
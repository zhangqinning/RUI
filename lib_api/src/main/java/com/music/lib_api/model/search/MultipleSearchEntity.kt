package com.music.lib_api.model.search

import com.music.lib_api.model.search.SynthesisSearchBean.ResultBean.*

class MultipleSearchEntity {
    var header: String? = null
    var footer: String? = null

    constructor(song: SongBean?) {
        this.song = song
    }

    constructor(djRadio: SynthesisSearchBean.ResultBean.DjRadioBean?) {
        this.djRadio = djRadio
    }

    constructor(artist: SynthesisSearchBean.ResultBean.ArtistBean?) {
        this.artist = artist
    }

    constructor(playList: PlayListBean?) {
        this.playList = playList
    }

    constructor(album: AlbumBeanX?) {
        this.album = album
    }

    constructor(video: SynthesisSearchBean.ResultBean.VideoBean?) {
        this.video = video
    }

    constructor(user: SynthesisSearchBean.ResultBean.UserBean?) {
        this.user = user
    }

    //单曲
    var song: SongBean? = null

    //电台
    var djRadio: SynthesisSearchBean.ResultBean.DjRadioBean? = null

    //歌手
    var artist: SynthesisSearchBean.ResultBean.ArtistBean? = null

    //歌单
    var playList: PlayListBean? = null

    //专辑
    var album: AlbumBeanX? = null

    //视频
    var video: SynthesisSearchBean.ResultBean.VideoBean? = null

    //相关搜索
    var sim_query: SimQueryBean? = null

    //用户
    var user: SynthesisSearchBean.ResultBean.UserBean? = null
    var rec_type: Any? = null
    var rec_query: List<String>? = null
    var order: List<String>? = null
}
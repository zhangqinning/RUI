package com.music.lib_network.retrofit

import com.music.lib_api.model.album.AlbumDetailBean
import com.music.lib_api.model.album.AlbumDynamicBean
import com.music.lib_api.model.album.AlbumSublistBean
import com.music.lib_api.model.artist.ArtistListBean
import com.music.lib_api.model.artist.ArtistSublistBean
import com.music.lib_api.model.banner.BannerBean
import com.music.lib_api.model.dj.*
import com.music.lib_api.model.mv.*
import com.music.lib_api.model.mv.MvBean
import com.music.lib_api.model.notification.CommonMessageBean
import com.music.lib_api.model.playlist.*
import com.music.lib_api.model.search.*
import com.music.lib_api.model.song.*
import com.music.lib_api.model.user.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "http://192.168.239.108:3000/"

interface ApiService {

    @GET("login/cellphone")
    fun login(
        @Query("phone") phone: String,
        @Query("password") password: String
    ): Observable<LoginBean>

    @GET("captcha/sent")
    fun capture(@Query("phone") phone: String): Observable<CommonMessageBean>

    @GET("register/cellphone")
    fun register(
        @Query("phone") phone: String,
        @Query("password") password: String,
        @Query("capture") capture: String
    ): Observable<LoginBean>

    @GET("video/url")
    fun getVideoUrl(
        @Query("id") id: String,
    ):Observable<VideoUrlBean>

    @GET("user/cloud")
    fun getUserCloudMusic()


    @GET("dj/detail")
    fun getRadioDetail(@Query("rid") id: String): Observable<DjDetailBean>

    @GET("dj/sublist")
    fun getDjSubList():Observable<DjSubListBean>

    @GET("dj/program")
    fun getRadioProgram(
        @Query("rid") id: String,
        @Query("asc") asc: Boolean
    ): Observable<DjProgramBean>

    @GET("album")
    fun getAlbumDetail(@Query("id") id: Long): Observable<AlbumDetailBean>


    @GET("album/detail/dynamic")
    fun getAlbumDynamic(@Query("id") id: Long): Observable<AlbumDynamicBean>


    @GET("logout")
    fun logout(): Observable<CommonMessageBean>

    @GET("banner")
    fun getBanner(@Query("type") type: String): Observable<BannerBean>

    @GET("dj/banner")
    fun getRadioBanner(): Observable<DjBannerBean>

    @GET("recommend/resource")
    fun getRecommendPlayList(): Observable<MainRecommendPlayListBean>

    @GET("recommend/songs")
    fun getDailyRecommend(): Observable<DailyRecommendBean>

    @GET("toplist")
    fun getTopList(): Observable<TopListBean>

    @GET("dj/recommend")
    fun getRadioRecommend(): Observable<DjRecommendBean>

    @GET("dj/recommend/type")
    fun getDjRecommend(@Query("type") type: Int): Observable<DjRecommendTypeBean>

    @GET("top/playlist")
    fun getPlayList(
        @Query("cat") type: String,
        @Query("limit") limit: Int
    ): Observable<RecommendPlayListBean>

    @GET("top/artists")
    fun getHotSingerList(): Observable<ArtistListBean>

    @GET("top/playlist/highquality")
    fun getHighquality(
        @Query("limit") limit: Int,
        @Query("before") before: Long
    ): Observable<HighQualityPlayListBean>

    @GET("playlist/catlist")
    fun getCatlist(): Observable<CatlistBean>

    @GET("playlist/detail")
    fun getPlaylistDetail(@Query("id") id: Long): Observable<PlaylistDetailBean>

    @GET("check/music")
    fun getMusicCanPlay(@Query("id") id: Long): Observable<MusicCanPlayBean>

    @GET("user/playlist")
    fun getUserPlaylist(@Query("uid") uid: Long): Observable<UserPlaylistBean>

    /**
     * 对歌单添加或删除歌曲
     * 参数:op: 从歌单增加单曲为 add, 删除为 del
     * pid: 歌单 id
     * tracks: 歌曲 id,可多个,用逗号隔开
     */
    @GET("playlist/tracks")
    fun trackPlayList(
        @Query("pid") id: Long,
        @Query("tracks") tracksId: String,
        @Query("op") op: String
    ): Observable<CommonMessageBean>

    @GET("user/event")
    fun getUserEvent(
        @Query("uid") uid: Long,
        @Query("limit") limit: Int,
        @Query("lasttime") time: Long
    ): Observable<UserEventBean>

    @GET("user/detail")
    fun getUserDetail(@Query("uid") uid: Long): Observable<UserDetailBean>

    // t:1关注 0:取消关注
    @GET("follow")
    fun getUserFollow(
        @Query("id") uid: Long,
        @Query("t") t: Int
    ): Observable<FollowBean>


    @GET("user/follows")
    fun getUserFollower(@Query("id") uid: Long): Observable<UserFollowerBean>

    @GET("user/followeds")
    fun getUserFollowed(@Query("id") uid: Long): Observable<UserFollowedBean>

    @GET("search/hot/detail")
    fun getSearchHotDetail(): Observable<HotSearchDetailBean>

    /**
     * 搜索
     * PS.type: 搜索类型；默认为 1 即单曲 , 取值意义 :
     * 1: 单曲, 10: 专辑, 100: 歌手, 1000: 歌单,
     * 1002: 用户, 1004: MV, 1006: 歌词, 1009: 电台,
     * 1014: 视频, 1018:综合
     */
    @GET("search")
    fun getSongSearch(
        @Query("keywords") keywords: String,
        @Query("type") type: Int
    ): Observable<SongSearchBean>

    @GET("search")
    fun getVideoSearch(
        @Query("keywords") keywords: String,
        @Query("type") type: Int
    ): Observable<FeedSearchBean>

    @GET("search")
    fun getSingerSearch(
        @Query("keywords") keywords: String,
        @Query("type") type: Int
    ): Observable<SingerSearchBean>

    @GET("search")
    fun getAlbumSearch(
        @Query("keywords") keywords: String,
        @Query("type") type: Int
    ): Observable<AlbumSearchBean>

    @GET("search")
    fun getPlayListSearch(
        @Query("keywords") keywords: String,
        @Query("type") type: Int
    ): Observable<PlayListSearchBean>

    @GET("search")
    fun getRadioSearch(
        @Query("keywords") keywords: String,
        @Query("type") type: Int
    ): Observable<RadioSearchBean>

    @GET("search")
    fun getUserSearch(
        @Query("keywords") keywords: String,
        @Query("type") type: Int
    ): Observable<UserSearchBean>

    @GET("search")
    fun getSynthesisSearch(
        @Query("keywords") keywords: String,
        @Query("type") type: Int
    ): Observable<SynthesisSearchBean>

    @GET("artists")
    fun getSingerHotSong(@Query("id") id: String): Observable<SingerSongSearchBean>

    /**
     * 歌手分类
     * type 1:男歌手 2:女歌手 3:乐队
     * area  -1:全部 7:华语(1) 96:欧美(2) 8:日本(3) 16韩国(4) 0:其他
     */
    @GET("artist/list")
    fun getSingerList(
        @Query("type") type: Int,
        @Query("area") area: Int
    ): Observable<ArtistListBean>

    @GET("artist/album")
    fun getSingerAlbum(@Query("id") id: Long): Observable<SingerAblumSearchBean>

    @GET("artist/desc")
    fun getSingerDesc(@Query("id") id: Long): Observable<SingerDescriptionBean>

    @GET("simi/artist")
    fun getSimiSinger(@Query("id") id: Long): Observable<SimiSingerBean>

    @GET("likelist")
    fun getLikeList(@Query("uid") uid: Long): Observable<LikeListBean>

    @GET("song/detail")
    fun getSongDetail(@Query("ids") ids: String): Observable<SongDetailBean>

    @GET("like")
    fun likeMusic(
        @Query("id") id: String,
        @Query("like") like: Boolean
    ): Observable<LikeMusicBean>

    @GET("comment/music")
    fun getMusicComment(@Query("id") id: String): Observable<PlayListCommentBean>

    @GET("comment/playlist")
    fun getPlayListComment(@Query("id") id: String): Observable<PlayListCommentBean>

    @GET("comment/album")
    fun getAlbumComment(@Query("id") id: String): Observable<PlayListCommentBean>

    @GET("comment/mv")
    fun getMvComment(@Query("id") id: String): Observable<PlayListCommentBean>

    @GET("video/group/list")
    fun getVideoGroup(): Observable<VideoGroupBean>

    @GET("video/group")
    fun getVideoTab(@Query("id") id: Long): Observable<VideoBean>

    @GET("video/detail")
    fun getVideoDetail(@Query("id") id: String): Observable<VideoDetailBean>

    @GET("video/timeline/recommend")
    fun getVideoRecommend(): Observable<VideoBean>

    @GET("related/allvideo")
    fun getVideoRelated(@Query("id") id: String): Observable<VideoRelatedBean>

    /**
     * 给评论点赞
     * id : 资源 id, 如歌曲 id, mv id
     * cid : 评论 id
     * t : 是否点赞 ,1 为点赞 ,0 为取消点赞
     * tpye: 数字 , 资源类型 , 对应歌曲 , mv, 专辑 , 歌单 , 电台, 视频对应以下类型
     * 0: 歌曲 1: mv 2: 歌单 3: 专辑 4: 电台 5: 视频 6: 动态
     */
    @GET("comment/like")
    fun likeComment(
        @Query("id") id: String,
        @Query("cid") cid: Long,
        @Query("t") t: Int,
        @Query("type") type: Int
    ): Observable<CommentLikeBean>

    /**
     * 给资源点赞
     * type : 资源类型 1: mv 4: 电台 5: 视频 6: 动态
     * t : 是否点赞 ,1 为点赞 ,0 为取消点赞
     * id: 资源 id
     * PS  注意：如给动态点赞，不需要传入 id，需要传入 threadId,可通过 event,/user/event 接口获取，如： /resource/liket=1&type=6&threadId=A_EV_2_6559519868_32953014
     */
    @GET("resource/like")
    fun likeResource(
        @Query("id") id: String,
        @Query("t") t: Int,
        @Query("type") type: Int
    ): Observable<CommentLikeBean>

    @GET("resource/like")
    fun likeEventResource(
        @Query("threadId") id: String,
        @Query("t") t: Int,
        @Query("type") type: Int
    ): Observable<CommentLikeBean>

    @GET("playmode/intelligence/list")
    fun getIntelligenceList(
        @Query("id") id: Long,
        @Query("pid") pid: Long
    ): Observable<PlayModeIntelligenceBean>

    // t=1 收藏 2 取消收藏
    @GET("playlist/subscribe")
    fun subscribePlayList(
        @Query("id") id: Long,
        @Query("t") t: Long
    ): Observable<CommonMessageBean>

    @GET("playlist/create")
    fun createPlayList(@Query("name") name: String): Observable<CommonMessageBean>

    // t=1 收藏 2 取消收藏
    @GET("album/sub")
    fun subscribeAlbum(
        @Query("id") id: Long,
        @Query("t") t: Long
    ): Observable<CommonMessageBean>

    // t=1 收藏 2 取消收藏
    @GET("video/sub")
    fun subscribeVideo(
        @Query("id") id: String,
        @Query("t") t: Long
    ): Observable<CommonMessageBean>

    @GET("top/album")
    fun getTopAlbum(@Query("limit") limit: Int): Observable<AlbumSearchBean.ResultBean>

    @GET("album/newest")
    fun getNewAlbum(): Observable<AlbumSearchBean.ResultBean>

    //PS.全部:0 华语:7  欧美:96 日本:8 韩国:16
    @GET("top/song")
    fun getTopSong(@Query("type") type: Int): Observable<NewSongBean>

    @GET("album/sublist")
    fun getAlbumSublist(): Observable<AlbumSublistBean>

    @GET("artist/sublist")
    fun getArtistSublist(): Observable<ArtistSublistBean>

    @GET("artist/sub")
    fun  //t =1 true  0 = false
            getSubArtist(
        @Query("id") id: Int,
        @Query("t") t: Int
    ): Observable<CommonMessageBean>

    @GET("mv/sublist")
    fun getMvSublist(): Observable<MvSublistBean>

    @GET("mv/sub")
    fun getMvSub(
        @Query("id") id: Int,
        @Query("t") t: Int
    ): Observable<CommentLikeBean>

    @GET("personal_fm")
    fun getMyFm(): Observable<MyFmBean>

    @GET("event")
    fun getMainEvent(): Observable<MainEventBean>

    @GET("lyric")
    fun getLyric(@Query("id") id: String): Observable<LyricBean>

    @GET("comment/playlist")
    fun getPlaylistComment(@Query("id") id: Long): Observable<PlayListCommentBean>

    @GET("comment/video")
    fun getVideoComment(@Query("id") id: String): Observable<PlayListCommentBean>

    @GET("dj/paygift")
    fun getDjPaygift(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Observable<DjPaygiftBean>

    @GET("dj/category/recommend")
    fun getDjCategoryRecommend(): Observable<DjCategoryRecommendBean>

    @GET("dj/catelist")
    fun getDjCatelist(): Observable<DjCatelistBean>

    // 1 订阅 0取消订阅
    @GET("dj/sub")
    fun getSubRadio(
        @Query("rid") rid: String,
        @Query("t") isSub: Int
    ): Observable<CommonMessageBean>

    @GET("top/mv")
    fun getMvTop(): Observable<MvTopBean>

    @GET("mv/detail")
    fun getMvDetail(@Query("mvid") mvId: String): Observable<MvInfoBean>

    @GET("top/mv")
    fun getMvTop(@Query("area") area: String): Observable<MvTopBean>

    @GET("mv/first")
    fun getFirstMv(
        @Query("area") area: String,
        @Query("limit") limit: Int
    ): Observable<MvBean>

    /**
     * 获取全部MV
     * area: 地区,可选值为全部,内地,港台,欧美,日本,韩国,不填则为全部
     * type: 类型,可选值为全部,官方版,原生,现场版,网易出品,不填则为全部
     * order: 排序,可选值为上升最快,最热,最新,不填则为上升最快
     * limit: 取出数量 , 默认为 30
     * offset: 偏移数量 , 用于分页 , 如 :( 页数 -1)*50, 其中 50 为 limit 的值 , 默认 为 0
     */
    @GET("mv/all")
    fun getAllMv(
        @Query("area") area: String,
        @Query("type") type: String,
        @Query("order") order: String,
        @Query("limit") limit: Int
    ): Observable<MvBean>

}
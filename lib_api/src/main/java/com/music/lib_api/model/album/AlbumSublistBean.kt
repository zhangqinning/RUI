package com.music.lib_api.model.album

import com.music.lib_api.model.search.AlbumSearchBean


class AlbumSublistBean {
    /**
     * data : [{"subTime":1568863447079,"msg":[],"alias":["战姬绝唱SYMPHOGEAR XV 角色歌6"],"artists":[{"img1v1Id":18686200114669624,"topicPerson":0,"alias":[],"picId":0,"albumSize":0,"musicSize":0,"briefDesc":"","followed":false,"img1v1Url":"https://p1.music.126.net/VnZiScyynLG7atLIZ2YPkw==/18686200114669622.jpg","trans":"","picUrl":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","name":"水樹奈々","id":17028,"img1v1Id_str":"18686200114669622"}],"picId":109951164342901890,"picUrl":"https://p1.music.126.net/68u951bt6jm-E4nCyuIuRw==/109951164342901886.jpg","name":"戦姫絶唱シンフォギアXV キャラクターソング6","id":81347636,"size":4,"transNames":[]}]
     * count : 1
     * hasMore : false
     * paidCount : 0
     * code : 200
     */
    var count = 0
    var isHasMore = false
    var paidCount = 0
    var code = 0
    var data: List<AlbumSearchBean.ResultBean.AlbumsBean>? = null
}
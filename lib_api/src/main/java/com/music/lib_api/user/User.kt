package com.music.lib_api.user

data class LoginBean(
    var LoginType: Int,
    var code: Int,
    var account: AccountBean,
    var token: String,
    var profile: Profile,
    var bindings: List<BindingBean>
)

data class AccountBean(
    var id: Long,
    var userName: String,
    var type: Int,
    var status: Int,
    var whitelistAuthority: Int,
    var createTime: Long,
    var salt: String,
    var tokenVersion: Int,
    var ban: Int,
    var baoyueVersion: Int,
    var donateVersion: Int,
    var vipType: Int,
    var anonimousUser: Boolean
)

data class Profile(
    var followed: Boolean,
    var backgroundUrl: String,
    var detailDescription: String,
    var userId: Long,
    var userType: Int,
    var vipType: Int,
    var gender: Int,
    var accountStatus: Int,
    var nickname: String,
    var birthday: Long,
    var avatarImgId: Long,
    var city: Int,
    var backgroundImgId: Long,
    var avatarUrl: String,
    var defaultAvatar: Boolean,
    var province: Int,
    var djStatus: Int,
//    var experts:Experts,
    var mutual: Boolean,
    var remarkName: Any?,
    var expertTags: Any?,
    var authStatus: Int,
    var description: String,
    var avatarImgIdStr: String,
    var backgroundImgIdStr: String,
    var signature: String,
    var authority: Int,
    var followeds: Int,
    var follows: Int,
    var eventCount: Int,
    var avatarDetail: Any?,
    var playlistCountL: Int,
    var playlistBeSubscribedCount: Int
)

data class BindingBean(
    var refreshTime: Int,
    var url: String,
    var userId: Int,
    var tokenJsonStr: String,
    var id: Long,
    var type: Int,
    var expiresIn: Long,
    var bindingTime: Long,
    var expired: Boolean
)

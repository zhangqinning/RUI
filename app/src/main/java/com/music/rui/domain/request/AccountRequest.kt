package com.music.rui.domain.request

import androidx.lifecycle.MutableLiveData
import com.music.lib_api.user.LoginBean
import com.music.lib_architecture.domain.request.BaseRequest

class AccountRequest : BaseRequest(){
    val loginData = MutableLiveData<LoginBean>()
}
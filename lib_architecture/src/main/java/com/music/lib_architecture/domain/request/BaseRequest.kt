package com.music.lib_architecture.domain.request

import com.music.lib_architecture.data.NetState
import com.music.lib_architecture.ui.callback.UnPeekLiveData
import com.music.lib_network.retrofit.ApiEngine

public open class BaseRequest {
    val netStateEvent = UnPeekLiveData<NetState>()

    fun requestLogin(phone:String ,password:String){
        ApiEngine.getInstance().
    }
}
package com.music.lib_architecture.domain.request

import com.music.lib_architecture.data.NetState
import com.music.lib_architecture.ui.callback.ProtectedUnPeekLiveData

open class BaseRequest {
    val netStateEvent = ProtectedUnPeekLiveData<NetState>()
}
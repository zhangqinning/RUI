package com.music.rui.ui.state

import androidx.lifecycle.ViewModel
import com.music.lib_architecture.ui.callback.UnPeekLiveData

class SharedViewModel :ViewModel() {

    val toCloseSlidePanelIfExpanded = UnPeekLiveData<Boolean>()
    val toCloseActivityIfAllowed = UnPeekLiveData<Boolean>()
    val toOpenCloseDrawer = UnPeekLiveData<Boolean>()


}
package com.music.rui.ui.state

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.lib_api.model.user.LoginBean

class DrawerViewModel : ViewModel() {
    val user: MutableLiveData<LoginBean> = MutableLiveData<LoginBean>()
}
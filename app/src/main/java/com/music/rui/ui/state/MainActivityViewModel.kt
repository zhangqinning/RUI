package com.music.rui.ui.state

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val isDrawerOpened = ObservableBoolean(false)

    val openDrawer = MutableLiveData(false)
    val allowDrawerOpen = MutableLiveData(true)
}
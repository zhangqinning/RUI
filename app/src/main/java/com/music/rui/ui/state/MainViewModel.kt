package com.music.rui.ui.state

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.music.rui.data.config.CHANNEL

class MainViewModel : ViewModel() {

    val initTabAndPage = ObservableBoolean()
    val channelArray: ObservableField<Array<CHANNEL>> = ObservableField<Array<CHANNEL>>()
    val limit = ObservableInt()
    var currentItem = ObservableInt()

    var vpOnPageScrooledPosition = ObservableInt()
    var vpOnPageScrooledPositionOffset = ObservableFloat()
    var vpOnPageScrooledPositionOffsetPixels = ObservableInt()
    var vponPageSelectedPosition = ObservableInt()
    var vpOnPageScrollStateChanged = ObservableInt()

    init {
        limit.set(1)
        currentItem.set(1)
        initTabAndPage.set(true)
        channelArray.set(arrayOf(CHANNEL.DISCORY, CHANNEL.PLAY))
    }
}
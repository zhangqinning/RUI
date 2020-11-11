package com.music.lib_architecture.ui.page

import android.content.res.Resources
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.music.lib_architecture.domain.manager.NetworkStateManager
import com.music.lib_architecture.utils.AdaptScreenUtils
import com.music.lib_architecture.utils.BarUtils
import com.music.lib_architecture.utils.ScreenUtils

abstract class BaseActivity : DataBindingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        BarUtils.setStatusBarLightMode(this, true)

        super.onCreate(savedInstanceState)

        lifecycle.addObserver(NetworkStateManager.getInstance())
    }

    override fun getResources(): Resources {
        return if (ScreenUtils.isPortrait()) {
            AdaptScreenUtils.adaptHeight(super.getResources(), 360)
        } else {
            AdaptScreenUtils.adaptWidth(super.getResources(), 640)
        }
    }

    protected open fun toggleSoftInput() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }

}
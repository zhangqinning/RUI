package com.music.lib_architecture.ui.page

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

open class BaseApplication : Application(), ViewModelStoreOwner {

    private val mAppViewModelStore = ViewModelStore()

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

}
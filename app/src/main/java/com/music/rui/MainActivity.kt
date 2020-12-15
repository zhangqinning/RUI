package com.music.rui

import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import com.music.lib_architecture.ui.page.BaseActivity
import com.music.lib_architecture.ui.page.DataBindingConfig
import com.music.rui.ui.state.MainActivityViewModel
import com.music.rui.ui.state.SharedViewModel

class MainActivity : BaseActivity() {

    lateinit var mMainActivityViewModel: MainActivityViewModel
    lateinit var mSharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mSharedViewModel.toCloseActivityIfAllowed.observeInActivity(this) {

        }


    }

    override fun initViewModel() {
        mMainActivityViewModel = getActivityScopeViewModel(MainActivityViewModel::class.java)
        mSharedViewModel = getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.activity_main, BR.vm, mMainActivityViewModel)
            .addBindingParam(BR.vm, EventHandler())
    }

    inner class EventHandler : DrawerLayout.SimpleDrawerListener() {
        override fun onDrawerOpened(drawerView: View) {
            super.onDrawerOpened(drawerView)
            mMainActivityViewModel.isDrawerOpened.set(true)
        }

        override fun onDrawerClosed(drawerView: View) {
            super.onDrawerClosed(drawerView)
            mMainActivityViewModel.isDrawerOpened.set(false)
            mMainActivityViewModel.openDrawer.value = false
        }
    }

}
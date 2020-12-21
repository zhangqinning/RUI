package com.music.rui.ui.page.drawer

import com.music.lib_architecture.ui.page.BaseFragment
import com.music.lib_architecture.ui.page.DataBindingConfig
import com.music.rui.BR
import com.music.rui.R
import com.music.rui.ui.state.DrawerViewModel

class DrawerFragment : BaseFragment() {

    lateinit var mDrawerViewModel: DrawerViewModel

    override fun initViewModel() {
        mDrawerViewModel = getFragmentScopeViewModel(DrawerViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_drawer, BR.vm, mDrawerViewModel)
    }
}
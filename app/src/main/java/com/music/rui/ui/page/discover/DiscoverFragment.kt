package com.music.rui.ui.page.discover

import com.music.lib_architecture.ui.page.BaseFragment
import com.music.lib_architecture.ui.page.DataBindingConfig
import com.music.rui.BR
import com.music.rui.R
import com.music.rui.ui.state.DiscoverViewModel

class DiscoverFragment : BaseFragment() {

    lateinit var mDiscoverViewModel: DiscoverViewModel

    override fun initViewModel() {
        mDiscoverViewModel = getFragmentScopeViewModel(DiscoverViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.delegate_discover, BR.vm, mDiscoverViewModel)
    }
}
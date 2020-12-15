package com.music.rui.ui.page

import android.os.Bundle
import android.view.View
import com.music.lib_architecture.ui.page.BaseFragment
import com.music.lib_architecture.ui.page.DataBindingConfig
import com.music.rui.ui.state.MainViewModel
import com.music.rui.ui.state.SharedViewModel
import com.music.rui.BR
import com.music.rui.R
import com.music.rui.ui.page.adapter.HomePagerAdapter

class MainFragment : BaseFragment() {

    lateinit var mMainFragment: MainViewModel
    lateinit var mSharedViewModel: SharedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSharedViewModel.toCloseSlidePanelIfExpanded.observeInFragment(
            this
        ) {
            mSharedViewModel.toCloseActivityIfAllowed.setValue(true)
        }
    }

    override fun initViewModel() {
        mMainFragment = getFragmentScopeViewModel(MainViewModel::class.java)
        mSharedViewModel = getApplicationScopeViewModel(SharedViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_main, BR.vm, mMainFragment)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(
                BR.adapter,
                HomePagerAdapter(childFragmentManager, mMainFragment.channelArray.get()!!)
            )
    }

    inner class ClickProxy {
        fun openMenu() {
            mSharedViewModel.toOpenCloseDrawer.setValue(true)
        }
    }
}

package com.music.rui.ui.page

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.music.lib_architecture.ui.page.BaseFragment
import com.music.lib_architecture.ui.page.DataBindingConfig
import com.music.rui.ui.state.MainViewModel
import com.music.rui.ui.state.SharedViewModel
import com.music.rui.BR
import com.music.rui.R
import com.music.rui.ui.page.adapter.HomePagerAdapter
import com.music.rui.ui.page.adapter.IndicatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

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
        val homePagerAdapter = HomePagerAdapter(childFragmentManager, mMainFragment.channelArray.get()!!)

        return DataBindingConfig(R.layout.fragment_main, BR.vm, mMainFragment)
            .addBindingParam(BR.click, ClickProxy())
            .addBindingParam(BR.adapter, homePagerAdapter)
            .addBindingParam(BR.indicatoradapter, CommonNavigator(requireContext()).apply {
                adapter = IndicatorAdapter(mMainFragment.channelArray.get()!!) { index ->
                    mMainFragment.currentItem.set(index)
                }
            })
            .addBindingParam(BR.onpagechangelistener,OnPageChangeListener())
    }

    inner class ClickProxy {
        fun openMenu() {
            mSharedViewModel.toOpenCloseDrawer.setValue(true)
        }
    }

    inner class OnPageChangeListener : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

        }

        override fun onPageSelected(position: Int) {
        }

        override fun onPageScrollStateChanged(state: Int) {
        }

    }
}

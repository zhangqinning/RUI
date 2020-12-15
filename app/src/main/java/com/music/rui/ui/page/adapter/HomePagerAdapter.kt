package com.music.rui.ui.page.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.music.rui.data.config.CHANNEL
import com.music.rui.ui.page.discover.DiscoverFragment
import com.music.rui.ui.page.play.PlayFragment

class HomePagerAdapter(fm: FragmentManager, private val data: Array<CHANNEL>) :

    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount() = data.size

    override fun getItem(position: Int): Fragment {
        return when (data[position]) {
            CHANNEL.PLAY -> PlayFragment()
            CHANNEL.DISCORY -> DiscoverFragment()
        }
    }
}
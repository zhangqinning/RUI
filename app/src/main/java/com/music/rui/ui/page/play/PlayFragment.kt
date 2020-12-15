package com.music.rui.ui.page.play

import com.music.lib_architecture.ui.page.BaseFragment
import com.music.lib_architecture.ui.page.DataBindingConfig
import com.music.rui.BR
import com.music.rui.R
import com.music.rui.ui.state.PlayViewModel

class PlayFragment : BaseFragment() {

    lateinit var mPlayViewModel: PlayViewModel

    override fun initViewModel() {
        mPlayViewModel = getFragmentScopeViewModel(PlayViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.delegate_play, BR.vm, mPlayViewModel)
    }
}
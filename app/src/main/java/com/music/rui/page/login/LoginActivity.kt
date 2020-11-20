package com.music.rui.page.login

import com.music.lib_architecture.ui.page.BaseActivity
import com.music.lib_architecture.ui.page.DataBindingConfig
import com.music.rui.R
import com.music.rui.ui.state.LoginViewModel

class LoginActivity : BaseActivity() {

    lateinit var mLoginViewMode: LoginViewModel

    override fun initViewModel() {
        mLoginViewMode = getActivityViewModel(LoginViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(
            R.layout.fragment_login,
            BR.vm,
            mLoginViewMode
        ).addBindingParam(BR.click, ClickProxy())
    }

    public class ClickProxy {
        public void phoneLogin()
        {

        }

        public void experience()
        {

        }
    }

    override fun onBackPressed() {
        finish()
    }

}
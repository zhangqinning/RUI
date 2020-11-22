package com.music.rui.page.login

import android.content.Intent
import com.music.lib_architecture.ui.page.BaseActivity
import com.music.lib_architecture.ui.page.DataBindingConfig
import com.music.rui.BR
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

    inner class ClickProxy {
        fun phoneLogin() {
            if (mLoginViewMode.agreeRule.get()) {
                startActivity(Intent(this@LoginActivity, PhoneLoginActivity::class.java))
                finish()
            } else {
                //提示用户点击同意条款
                showShortToast("请点击同意《用户协议》和《隐私政策》《儿童隐私政策》")
            }
        }

        fun experience() {

        }
    }

    override fun onBackPressed() {
        finish()
    }

}
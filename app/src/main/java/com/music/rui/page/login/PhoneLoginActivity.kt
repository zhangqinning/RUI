package com.music.rui.page.login

import com.music.lib_architecture.ui.page.BaseActivity
import com.music.lib_architecture.ui.page.DataBindingConfig
import com.music.rui.R
import com.music.rui.ui.state.PhoneLoginViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class PhoneLoginActivity : BaseActivity() {

    lateinit var mPhoneLoginViewModel: PhoneLoginViewModel

    val timer = Observable.interval(0, 1, TimeUnit.SECONDS)
        .take(60 + 1)
        .map {
            60 - it
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    override fun initViewModel() {
        mPhoneLoginViewModel = getActivityViewModel(PhoneLoginViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.de)
    }

}
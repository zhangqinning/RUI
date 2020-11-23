package com.music.rui.ui.state

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.music.rui.domain.request.AccountRequest

class PhoneLoginViewModel : ViewModel() {
    //输入手机号界面
    val showInputPhoneView = ObservableBoolean()

    //输入密码界面
    val showInputPasswordView = ObservableBoolean()

    //忘记密码界面
    val showForgetPasswordView = ObservableBoolean()

    //显示验证码界面
    val showCaptureView = ObservableBoolean()

    //使能重新获取验证码按钮
    val enableCaptureButton = ObservableBoolean()

    //密码hint
    val passwordHint = ObservableField<String>()

    //界面标题
    val title = ObservableField<String>()

    //1min 倒计时输入验证码
    val countDownText = ObservableField<String>()

    //手机号
    val phone = ObservableField<String>()

    //密码
    val password = ObservableField<String>()

    val accountRequest = AccountRequest()

    init {
        showInputPhoneView.set(true)
        showInputPasswordView.set(true)
        title.set("手机号登陆")
        enableCaptureButton.set(false)
    }

}
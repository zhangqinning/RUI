package com.music.rui.ui.state

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.music.rui.domain.request.AccountRequest

class PhoneLoginViewModel : ViewModel() {

    val phone = ObservableBoolean()
    val password = ObservableField<String>()
    val showInputPhoneView = ObservableBoolean()
    val showInputPasswordView = ObservableBoolean()
    val showForgetPasswordView = ObservableBoolean()
    val showCaptureView = ObservableBoolean()
    val enableCaptureButton = ObservableBoolean()
    val passwordHint = ObservableField<String>()
    val title = ObservableField<String>()
    val countDownText = ObservableField<String>()

    val accountRequest = AccountRequest()

    init {
        showInputPhoneView.set(true)
        showInputPasswordView.set(true)
        title.set("手机号登陆")
        enableCaptureButton.set(false)
    }

}
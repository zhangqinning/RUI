package com.music.rui.page.login

import android.content.Intent
import android.os.Bundle
import com.music.lib_architecture.ui.page.BaseActivity
import com.music.lib_architecture.ui.page.DataBindingConfig
import com.music.lib_architecture.utils.Utils
import com.music.lib_common_ui.utils.SharePreferenceUtil
import com.music.lib_common_ui.utils.ValidateUtils
import com.music.lib_common_ui.widget.CaptchaView
import com.music.rui.BR
import com.music.rui.MainActivity
import com.music.rui.R
import com.music.rui.ui.state.PhoneLoginViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.ResourceObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class PhoneLoginActivity : BaseActivity() {

    lateinit var mPhoneLoginViewModel: PhoneLoginViewModel

    private val timer = Observable.interval(0, 1, TimeUnit.SECONDS)
        .take(60 + 1)
        .map {
            60 - it
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    private val listener = CaptchaView.OnInputListener { code ->
        mPhoneLoginViewModel.run {
            accountRequest.register(phone.get()!!, password.get()!!, code)
        }
    }

    override fun initViewModel() {
        mPhoneLoginViewModel = getActivityViewModel(PhoneLoginViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.delegate_phone_login, BR.vm, mPhoneLoginViewModel)
            .addBindingParam(BR.proxy, ClickProxy())
            .addBindingParam(BR.listener, listener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPhoneLoginViewModel.accountRequest.loginData.observe(this) { loginBean ->
            if (loginBean.code == 200) {
                SharePreferenceUtil.getInstance(Utils.getApp())
                    .saveUserInfo(loginBean, mPhoneLoginViewModel.phone.get())
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                showLongToast(loginBean.msg)
            }
        }

        mPhoneLoginViewModel.accountRequest.captureData.observe(this) { message ->
            if (message.code == 200) {
                mPhoneLoginViewModel.run {
                    showCaptureView.set(true)
                    showInputPasswordView.set(true)
                    enableCaptureButton.set(false)
                }
                timer.subscribe(object : ResourceObserver<Long>() {
                    override fun onNext(t: Long?) {
                        mPhoneLoginViewModel.countDownText.set(t.toString() + "秒")
                    }

                    override fun onError(e: Throwable?) {

                    }

                    override fun onComplete() {
                        mPhoneLoginViewModel.run {
                            countDownText.set("重新获取")
                            enableCaptureButton.set(true)
                        }
                    }
                })
            } else {
                showLongToast(message.msg)
            }
        }
    }

    inner class ClickProxy {
        fun back() {
            mPhoneLoginViewModel.run {
                if (showInputPhoneView.get()) {
                    finish()
                } else if (showForgetPasswordView.get() && showInputPasswordView.get()) {
                    showForgetPasswordView.set(false)
                    title.set("手机号码登陆")
                    passwordHint.set("请输入密码")
                } else if (showInputPhoneView.get() && !showForgetPasswordView.get()) {
                    showInputPasswordView.set(false)
                    showInputPhoneView.set(true)
                }
            }
        }

        fun next() {
            mPhoneLoginViewModel.run {
                if (ValidateUtils.isMobile(phone.get())) {
                    showInputPhoneView.set(false)
                    showInputPasswordView.set(true)
                    passwordHint.set("请输入密码")
                } else {
                    showShortToast("请输入正确格式的手机号")
                }
            }
        }

        fun login() {
            mPhoneLoginViewModel.run {
                if (showForgetPasswordView.get()) {
                    password.get()?.let {
                        if (it.length in 3..20 && ValidateUtils.isPassword(it)) {
                            accountRequest.sendCapture(phone.get()!!)
                        }
                    }
                } else {
                    accountRequest.requestLogin(phone.get()!!, password.get()!!)
                }
            }
        }

        //忘记密码
        fun forgetPassword() {
            mPhoneLoginViewModel.showForgetPasswordView.set(true)
            mPhoneLoginViewModel.title.set("忘记密码")
            mPhoneLoginViewModel.passwordHint.set("请设置登录密码,8-20位字符,至少字母/数字/符号两种组合")
        }

        //重新获取验证码  只有在倒计时结束 显示重新获取 后才可以点击此按钮
        fun reObtainCpatureCode() {
            mPhoneLoginViewModel.accountRequest.sendCapture(mPhoneLoginViewModel.phone.get()!!)
        }
    }

}
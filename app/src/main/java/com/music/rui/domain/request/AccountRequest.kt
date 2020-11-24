package com.music.rui.domain.request

import androidx.lifecycle.MutableLiveData
import com.music.lib_api.notification.CommonMessageBean
import com.music.lib_api.user.LoginBean
import com.music.lib_architecture.domain.request.BaseRequest
import com.music.lib_network.retrofit.ApiEngine
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class AccountRequest : BaseRequest() {
    val loginData = MutableLiveData<LoginBean>()
    val captureData = MutableLiveData<CommonMessageBean>()

    fun requestLogin(phone: String, password: String) {
        ApiEngine.getInstance().getApiService().login(phone, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LoginBean> {


                override fun onNext(loginBean: LoginBean) {
                    Timber.v(loginBean.toString())
                    loginData.postValue(loginBean)
                }

                override fun onError(e: Throwable?) {
                    Timber.v("requestLogin error ${e.toString()}")
                }

                override fun onComplete() {
                    Timber.v("requestLogin onComplete")
                }

                override fun onSubscribe(d: Disposable?) {
                }
            })
    }

    fun sendCapture(phone: String) {
        ApiEngine.getInstance().getApiService().capture(phone)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<CommonMessageBean> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(t: CommonMessageBean?) {
                    captureData.postValue(t)
                }

                override fun onError(e: Throwable?) {
                }

                override fun onComplete() {
                }
            })
    }

    fun register(phone: String, password: String, code: String) {
        ApiEngine.getInstance().getApiService().register(phone, password, code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LoginBean> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(t: LoginBean?) {
                    loginData.postValue(t)
                }

                override fun onError(e: Throwable?) {
                }

                override fun onComplete() {
                }

            })
    }
}
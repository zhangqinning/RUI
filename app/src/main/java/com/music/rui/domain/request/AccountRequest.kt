package com.music.rui.domain.request

import androidx.lifecycle.MutableLiveData
import com.music.lib_api.user.LoginBean
import com.music.lib_architecture.domain.request.BaseRequest
import com.music.lib_network.retrofit.ApiEngine
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AccountRequest : BaseRequest() {
    val loginData = MutableLiveData<LoginBean>()

    fun requestLogin(phone: String, password: String) {
        ApiEngine.getInstance().getApiService().login(phone, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LoginBean> {


                override fun onNext(loginBean: LoginBean) {
                    loginData.postValue(loginBean)
                }

                override fun onError(e: Throwable?) {
                }

                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable?) {
                }

            })

    }


}
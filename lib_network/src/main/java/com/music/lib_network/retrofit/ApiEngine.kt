package com.music.lib_network.retrofit

import com.music.lib_network.interceptor.NetWorkInterceptor
import com.music.lib_network.interceptor.ResposeInterceptor
import retrofit2.Retrofit


class ApiEngine private constructor() {

     lateinit var retrofit: Retrofit

    init {
        // 网络拦截器
        val netWorkInterceptor = NetWorkInterceptor()
        // 解析返回结果的interceptor
        val responseListener = ResposeInterceptor()


    }

    companion object {
        fun getInstance(): ApiEngine {
            return Holder.instant
        }
    }

    private object Holder {
        val instant by lazy { ApiEngine() }
    }
}
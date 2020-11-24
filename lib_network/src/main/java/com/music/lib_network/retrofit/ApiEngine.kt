package com.music.lib_network.retrofit

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.Gson
import com.music.lib_architecture.utils.Utils
import com.music.lib_network.interceptor.NetWorkInterceptor
import com.music.lib_network.interceptor.ResponseInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiEngine private constructor() {

    private object Holder {
        val instant by lazy { ApiEngine() }
    }

    var retrofit: Retrofit

    init {
        // 网络拦截器
        val netWorkInterceptor = NetWorkInterceptor()
        // 解析返回结果的interceptor
        val responseListener = ResponseInterceptor()

        val cookieJar = PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(Utils.getApp())
        )

        val client = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(netWorkInterceptor)
            .addInterceptor(responseListener)
            .cookieJar(cookieJar)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

    }

    companion object {
        fun getInstance(): ApiEngine {
            return Holder.instant
        }
    }

    fun getApiService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}
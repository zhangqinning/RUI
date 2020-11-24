package com.music.lib_network.retrofit

import com.music.lib_api.notification.CommonMessageBean
import com.music.lib_api.user.LoginBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

const val BASE_URL = "http://192.168.239.108:3000/"

interface ApiService {

    @GET("login/cellphone")
    fun login(
        @Query("phone") phone: String,
        @Query("password") password: String
    ): Observable<LoginBean>

    @GET("captcha/sent")
    fun capture(@Query("phone") phone: String): Observable<CommonMessageBean>

    @GET("register/cellphone")
    fun register(
        @Query("phone") phone: String,
        @Query("password") password: String,
        @Query("capture") capture: String
    ): Observable<LoginBean>
}
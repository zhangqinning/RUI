package com.music.lib_network.retrofit

import com.music.lib_api.user.LoginBean
import retrofit2.http.Query
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET


const val BASE_URL = "http://62.234.57.125:3000/"

interface ApiService {

    @GET("login/callphone")
    fun login(@Query("phone") phone: String, @Query("password") password: String): Observable<LoginBean>

}
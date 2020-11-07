package com.music.lib_network.retrofit

import retrofit2.http.Query
import io.reactivex.rxjava3.core.Observable


const val BASE_URL = "http://62.234.57.125:3000/"

interface ApiService {

    fun login(@Query("phone") phone: String, @Query("password") password: String): Observable<>

}
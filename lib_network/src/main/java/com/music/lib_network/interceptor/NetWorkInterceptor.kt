package com.music.lib_network.interceptor

import com.music.lib_network.utils.isConnected
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

class NetWorkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        // 无网络时强制使用缓存
        if (!isConnected()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
        }

        val response = chain.proceed(request);

        if (isConnected()) {
            // 有网络时，设置超时为0
            val maxStale = 0
            response.newBuilder()
                    .header("Cache-Control", "public,max-age=$maxStale")
                    .build()
        } else {
            // 无网络时，设置超时为1天
            val maxStale = 60 * 60 * 24;
            response.newBuilder()
                    .header("Cache-Control", "public,only-if-cache,max-stale$maxStale")
                    .build()
        }
        return response

    }
}
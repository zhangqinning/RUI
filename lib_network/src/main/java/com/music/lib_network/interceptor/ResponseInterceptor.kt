package com.music.lib_network.interceptor

import android.util.Log
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.*

class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val t1 = System.nanoTime()
        val response = chain.proceed(request)

        val t2 = System.nanoTime()
        val responseBody = response.body()

        if (response.code() != 200) {
            return response
        }

        val contentLength = responseBody?.contentLength()

        contentLength?.takeIf {
            !bodyEncoded((response.headers()))
        }?.also { it ->
            val source = responseBody.source()
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer
            var charset = UTF8

            responseBody.contentType()?.takeIf {
                it.charset(UTF8) != null
            }?.also {
                charset = it.charset(UTF8)!!
            } ?: return response

            if (it != 0L) {
                val result = buffer.clone().readString(charset)
                Log.d(TAG, " response.url():" + response.request().url())
                Log.d(TAG, " response.body():$result")
                Log.d(TAG, " 响应时间: " + (t2 - t1) / 1e6 + "ms")
            }
        }
        return response
    }


    private fun bodyEncoded(headers: Headers): Boolean {
        headers.get("Content-Encoding")?.also {
            return it.toLowerCase(Locale.ROOT) != "identity"
        }
        return false
    }


    companion object {
        val UTF8: Charset = StandardCharsets.UTF_8
        const val TAG = "ResponseInterceptor"
    }

}
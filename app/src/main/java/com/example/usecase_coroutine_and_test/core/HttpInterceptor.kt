package com.example.usecase_coroutine_and_test.core

import okhttp3.Interceptor
import okhttp3.Response

class HttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val response = chain.proceed(request)

        val sentAt = response.sentRequestAtMillis
        val receiveAt = response.receivedResponseAtMillis
        val totalTime = receiveAt - sentAt
        println("Request to ${request.url} took $totalTime ms")
        return chain.proceed(chain.request())
    }
}
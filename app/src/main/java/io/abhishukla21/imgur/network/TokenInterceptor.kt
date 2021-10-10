package io.abhishukla21.imgur.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

@Singleton
class TokenInterceptor(private var accessToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken")
            .build()
        return chain.proceed(newRequest)
    }

    fun updateAccessToken(accessToken: String) {
        this.accessToken = accessToken
    }
}
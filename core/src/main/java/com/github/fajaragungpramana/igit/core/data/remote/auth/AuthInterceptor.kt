package com.github.fajaragungpramana.igit.core.data.remote.auth

import com.github.fajaragungpramana.igit.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader("Authorization", "token ${BuildConfig.API_TOKEN}")

        return chain.proceed(requestBuilder.build())
    }

}
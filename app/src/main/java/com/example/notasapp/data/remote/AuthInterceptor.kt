package com.example.notasapp.data.remote

import com.example.notasapp.data.local.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

// agrega el token en el header Authorization de todas las peticiones
class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = SessionManager.obtenerToken()
        val request = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }
        return chain.proceed(request)
    }
}

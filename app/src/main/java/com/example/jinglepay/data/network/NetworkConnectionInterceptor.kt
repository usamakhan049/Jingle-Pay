package com.example.jinglepay.data.network

import android.content.Context
import android.util.Log
import com.example.jinglepay.R
import com.example.jinglepay.utils.NoInternetConnectionException
import com.example.jinglepay.utils.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkConnectionInterceptor @Inject constructor(
    context: Context
) : Interceptor {

    private val appliationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!appliationContext.isNetworkAvailable())
            throw NoInternetConnectionException(appliationContext.getString(R.string.make_sure_you_have_active_internet_connection))

        Log.d("TAG url",chain.request().url().toString())
        return chain.proceed(chain.request())
    }
}
package com.fw.base_library.net

import android.text.TextUtils
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/9 22:49
 *    desc   :
 *    version: 1.0
 */
object RetrofitUtil {
    private const val CALL_TIMEOUT = 10L
    private const val CONNECT_TIMEOUT = 20L
    private const val IO_TIMEOUT = 20L
    private var baseUrl = ""

    private val retrofit: Retrofit by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().let {
            it.redactHeader("zff=====>")
            it.level = HttpLoggingInterceptor.Level.BODY
            it
        }
        //okHttpclient
        val client = OkHttpClient.Builder()
            .callTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .retryOnConnectionFailure(true)
            .build()
        if(TextUtils.isEmpty(baseUrl)){
            throw IllegalStateException("请设置baseUrl...")
        }
        Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun setBaseUrl(baseUrl: String){
        if(!TextUtils.isEmpty(baseUrl)){
            this.baseUrl = baseUrl
        }
    }

    /**
     * 获取接口请求
     * @param service Class<T>
     * @return T
     */
    fun <T> getApiService(service: Class<T>): T{
        return retrofit.create(service)
    }


}
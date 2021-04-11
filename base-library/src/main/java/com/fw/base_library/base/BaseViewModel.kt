package com.fw.base_library.base

import androidx.lifecycle.ViewModel
import com.fw.base_library.net.ApiException
import com.fw.base_library.util.LogUtil
import com.fw.base_library.util.ToastUtil
import com.google.gson.JsonParseException
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/10 16:26
 *    desc   :
 *    version: 1.0
 */
typealias Error = (Exception) -> Unit
typealias Cancel = (Exception) -> Unit
typealias Block<T> = suspend () -> T

open class BaseViewModel: ViewModel() {
    /**
     * http请求 协程封装
     * @param block Function0<Unit>
     * @param error Function1<Exception, Unit>?
     * @param cancel Function1<Exception, Unit>?
     * @param showErrorToast Boolean
     */
    fun launch(error: Error? = null, cancel: Cancel? = null
               , showErrorToast: Boolean = true, block: Block<Unit>){
        //创建协程，用于调用请求挂起函数    设置主线程
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            try {
                block()
            } catch (e: Exception) {
                onError(e, showErrorToast)
                if(e is CancellationException){
                    cancel?.invoke(e)
                }else{
                    error?.invoke(e)
                }
            }
        }
    }

    fun <T> async(block: Block<T>): Deferred<T> {
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        //阻塞
        return coroutineScope.async {
            block.invoke()
        }
    }

    /**
     * 取消协程
     * @param job Job
     */
    fun cancelJob(job: Job){
        if (job.isActive && !job.isCompleted && !job.isCancelled) {
            job.cancel()
        }
    }

    /**
     * 统一处理异常
     * @param e Exception
     * @param showErrorToast Boolean
     */
    private fun onError(e: Exception, showErrorToast: Boolean){
        if(showErrorToast && e !is CancellationException){
            ToastUtil.show(BaseApp.application, e.toString())
        }
        LogUtil.e("onError(包含取消):" + e.message)
        when (e) {
            //取消异常
            is CancellationException -> {}
            //自定义异常
            is ApiException -> {
                when (e.code) {
                    //登录失效，重新登录
                    -1001 -> {
                    }
                    else -> {}
                }
            }
            // 网络请求失败
            is ConnectException, is SocketTimeoutException, is UnknownHostException, is HttpException -> {
//                LogUtil.e("网络请求失败" + e.message)
            }
            // 数据解析错误
            is JsonParseException -> {
//                LogUtil.e("数据解析错误" + e.message)
            }
            else -> {}
        }
    }
}
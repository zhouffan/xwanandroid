package org.fw.x_wanandroid.test

import androidx.lifecycle.MutableLiveData
import com.fw.base_library.base.BaseViewModel
import com.fw.base_library.net.RetrofitUtil
import com.fw.base_library.util.LogUtil
import kotlinx.coroutines.*
import org.fw.x_wanandroid.API
import org.fw.x_wanandroid.bean.Banner

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/8 22:46
 *    desc   :
 *    version: 1.0
 */
class TestViewModel: BaseViewModel() {
    private val repository by lazy { TestRepository() }

    val num: MutableLiveData<Int> = MutableLiveData()
    val bannerData: MutableLiveData<MutableList<Banner>> = MutableLiveData()

    fun add(value: Int){
        num.value = num.value?.plus(value)
    }

    fun getBanner(){
        LogUtil.i("1:"+Thread.currentThread().toString())
        //异步请求数据，重置数据，刷新UI
        launch{
            LogUtil.i("2:"+Thread.currentThread().toString())
            //IO线程请求后返回
            val banner = repository.getBanner()
            bannerData.value = banner
            LogUtil.i("3:"+Thread.currentThread().toString())
        }
        LogUtil.i("4:"+Thread.currentThread().toString())
//        launch(error = {}, showErrorToast = true){
//
//        }
    }

    fun getBannerIO(){
        //异步请求数据，重置数据，刷新UI
        launch{
            //IO线程请求后返回
            val banner = repository.getBannerIO()
            bannerData.value = banner
        }
    }

    fun getBanner2(){
        LogUtil.i("1:"+Thread.currentThread().toString())
        //异步请求数据，重置数据，刷新UI
        val s = async {
            LogUtil.i("2:"+Thread.currentThread().toString())
            val apiService = RetrofitUtil.getApiService(API::class.java)
            apiService.getBanner().data()
        }
        val s2 = async {
            LogUtil.i("2:"+Thread.currentThread().toString())
            val apiService = RetrofitUtil.getApiService(API::class.java)
            apiService.getBanner().data()
        }
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
           val d1  = s.await()
           val d2  = s2.await()
        }

    }
}
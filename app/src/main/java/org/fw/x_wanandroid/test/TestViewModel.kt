package org.fw.x_wanandroid.test

import androidx.lifecycle.MutableLiveData
import com.fw.base_library.base.BaseViewModel
import com.fw.base_library.net.RetrofitUtil
import com.fw.base_library.util.LogUtil
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
            val apiService = RetrofitUtil.getApiService(API::class.java)
            val banner = apiService.getBanner().data()
            bannerData.value = banner
        }

    }
}
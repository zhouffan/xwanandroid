package org.fw.x_wanandroid.test

import com.fw.base_library.net.RetrofitUtil
import com.fw.base_library.util.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.fw.x_wanandroid.API
import org.fw.x_wanandroid.repository.BaseRepository

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/11 01:02
 *    desc   :
 *    version: 1.0
 */
class TestRepository : BaseRepository(){

    /**
     * 可能会因为主线程请求阻塞导致anr
     * @return MutableList<Banner>
     */
    suspend fun getBanner0() = apiService.getBanner().data()

    /**
     * 使异步请求在IO协程中完成，耗时操作避免anr
     * @return MutableList<Banner>
     */
    suspend fun getBanner() = withContext(Dispatchers.IO) {
        LogUtil.i("5:"+Thread.currentThread().toString())
        apiService.getBanner().data()
    }

    /**
     * 继续封装
     * @return MutableList<Banner>
     */
    suspend fun getBannerIO() = io {
        apiService.getBanner().data()
    }

}
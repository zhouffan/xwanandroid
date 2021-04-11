package org.fw.x_wanandroid.repository

import com.fw.base_library.net.RetrofitUtil
import com.fw.base_library.util.LogUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.fw.x_wanandroid.API

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/11 01:01
 *    desc   :
 *    version: 1.0
 */
open class BaseRepository {
     val apiService = RetrofitUtil.getApiService(API::class.java)

     /**
      * io线程中请求接口
      * 不用可能会因为主线程请求阻塞导致anr
      * @param block SuspendFunction0<T>
      * @return T
      */
     suspend fun <T> io(block: suspend () -> T):T = withContext(Dispatchers.IO) {
          block.invoke()
     }
}
package org.fw.x_wanandroid

import org.fw.x_wanandroid.bean.Banner
import org.fw.x_wanandroid.bean.BaseBean
import retrofit2.http.GET

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/9 23:16
 *    desc   :
 *    version: 1.0
 */
interface API {
    companion object {
        const val BASE_URL = "https://www.wanandroid.com/"
    }

    //首页banner
    @GET("banner/json")
    suspend fun getBanner(): BaseBean<MutableList<Banner>>
}
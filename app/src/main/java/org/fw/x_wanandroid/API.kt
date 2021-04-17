package org.fw.x_wanandroid

import org.fw.x_wanandroid.bean.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

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
    fun getBanner2(): Call<BaseBean<MutableList<Banner>>>

    //首页-banner
    @GET("banner/json")
    suspend fun getBanner(): BaseBean<MutableList<Banner>>

    //首页-文章列表
    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page:Int): BaseBean<Article>

    //首页-置顶文章
    @GET("article/top/json")
    suspend fun getTopArticleList(): BaseBean<MutableList<Data>>


}
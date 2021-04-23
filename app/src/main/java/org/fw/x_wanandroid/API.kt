package org.fw.x_wanandroid

import org.fw.x_wanandroid.bean.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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

    /**
     * 广场列表数据
     * https://wanandroid.com/user_article/list/0/json
     * @param page 页码拼接在url上从0开始
     */
    @GET("user_article/list/{page}/json")
    suspend fun getPlazaList(@Path("page") page:Int): BaseBean<Article>

    /**
     * 获取公众号列表
     * http://wanandroid.com/wxarticle/chapters/json
     */
    @GET("/wxarticle/chapters/json")
    suspend fun getWXChapters(): BaseBean<MutableList<Wechat>>

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=168
     * @param page
     * @param cid
     */
    @GET("article/list/{page}/json")
    suspend fun getKnowledgeList(@Path("page") page: Int, @Query("cid") cid: Int):
            BaseBean<Article>

    /**
     * 获取知识体系
     * http://www.wanandroid.com/tree/json
     */
    @GET("tree/json")
    suspend fun getKnowledgeTree(): BaseBean<MutableList<KnowledgeTree>>

    /**
     * 导航数据
     * http://www.wanandroid.com/navi/json
     */
    @GET("navi/json")
    suspend fun getNavigationList(): BaseBean<MutableList<NavigationBean>>
}
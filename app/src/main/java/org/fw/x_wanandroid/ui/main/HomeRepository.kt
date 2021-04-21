package org.fw.x_wanandroid.ui.main

import com.fw.base_library.base.Block
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.fw.x_wanandroid.repository.BaseRepository
import retrofit2.http.Query

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/15 22:44
 *    desc   :
 *    version: 1.0
 */
class HomeRepository:  BaseRepository(){

    /**
     * 获取banner
     * @return BaseBean<MutableList<Banner>>
     */
    suspend fun getBanner() = io {
        apiService.getBanner()
    }

    /**
     * 首页文章列表
     * @param page Int
     * @return BaseBean<Article>
     */
//    suspend fun getHomeArticleList(page: Int)=io {
//        apiService.getArticleList(page)
//    }
    suspend fun getHomeArticleList(page: Int)= async {
        apiService.getArticleList(page)
    }

    suspend fun getTopArticleList()= async{
        apiService.getTopArticleList()
    }

    /**
     * 广场列表
     * @param page Int
     */
    suspend fun getPlazaList(page: Int) = io{
        apiService.getPlazaList(page)
    }

    /**
     * 获取公众号列表
     * @return BaseBean<MutableList<Wechat>>
     */
    suspend fun getWXChapters() = io{
        apiService.getWXChapters()
    }

    /**
     * 公众号 -个人- 文章 知识体系
     * @param page Int
     * @param cid Int
     * @return BaseBean<Article>
     */
    suspend fun getKnowledgeList(page: Int,  cid: Int) = io{
        apiService.getKnowledgeList(page, cid)
    }

    /**
     * 获取知识体系
     * @return BaseBean<KnowledgeTree>
     */
    suspend fun getKnowledgeTree() = io{
        apiService.getKnowledgeTree()
    }

    /**
     * 导航数据
     * @return BaseBean<MutableList<NavigationBean>>
     */
    suspend fun getNavigationList() = io{
        apiService.getNavigationList()
    }
}
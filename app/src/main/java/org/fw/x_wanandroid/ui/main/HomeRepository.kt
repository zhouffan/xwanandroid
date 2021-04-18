package org.fw.x_wanandroid.ui.main

import com.fw.base_library.base.Block
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.fw.x_wanandroid.repository.BaseRepository

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

}
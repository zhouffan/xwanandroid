package org.fw.x_wanandroid.ui.main

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
    suspend fun getHomeArticleList(page: Int)=io {
        apiService.getArticleList(page)
    }
}
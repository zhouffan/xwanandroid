package org.fw.x_wanandroid.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fw.base_library.base.BaseViewModel
import org.fw.x_wanandroid.bean.Article
import org.fw.x_wanandroid.bean.Banner
import org.fw.x_wanandroid.bean.BaseBean
import org.fw.x_wanandroid.ui.main.HomeRepository

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/13 21:38
 *    desc   :
 *    version: 1.0
 */
class HomeViewModel: BaseViewModel() {
    private val repository by lazy {
        HomeRepository()
    }

    val bannerData = MutableLiveData<MutableList<Banner>>()
    val articleData = MutableLiveData<Article>()

    /**
     * 发起请求
     */
    fun getBannerData(){
        launch {
            bannerData.value = repository.getBanner().data()
        }
    }

    /**
     * 获取首页文章
     * @param page Int
     */
//    fun getArticleData(page: Int) = launch {
//        articleData.value = repository.getHomeArticleList(page).data()
//    }

    /**
     * 多个请求同时结束时返回
     * 包含置顶数据
     * @param page Int
     */
    fun getAllArticleData(page: Int) = launch {
        val homeArticleList = repository.getHomeArticleList(page)
        val topArticleList = repository.getTopArticleList()
        val homeArticle = homeArticleList.await().data()
        val topArticle = topArticleList.await().data()
        //重新封装数据
        if(topArticle.size > 0){
            //置顶标识
            topArticle.forEach { it.top = true }
            topArticle.addAll(homeArticle.datas)
            homeArticle.datas.clear()
            homeArticle.datas.addAll(topArticle)
        }
        articleData.value = homeArticle
    }
}
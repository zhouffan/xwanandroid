package org.fw.x_wanandroid.ui.main.wechat

import androidx.lifecycle.MutableLiveData
import com.fw.base_library.base.BaseViewModel
import org.fw.x_wanandroid.bean.Article
import org.fw.x_wanandroid.bean.Wechat
import org.fw.x_wanandroid.ui.main.HomeRepository

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/13 21:38
 *    desc   :
 *    version: 1.0
 */
class PersonalArticleModel: BaseViewModel() {
    private val repository by lazy {
        HomeRepository()
    }

    val articleData = MutableLiveData<Article>()

    /**
     * 发起请求
     */
    fun getKnowledgeList(page: Int,  cid: Int){
        launch {
            articleData.value = repository.getKnowledgeList(page, cid).data()
        }
    }

}
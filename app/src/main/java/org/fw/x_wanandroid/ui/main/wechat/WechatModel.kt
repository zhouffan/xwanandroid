package org.fw.x_wanandroid.ui.main.wechat

import androidx.lifecycle.MutableLiveData
import com.fw.base_library.base.BaseViewModel
import org.fw.x_wanandroid.bean.Wechat
import org.fw.x_wanandroid.ui.main.HomeRepository

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/13 21:38
 *    desc   :
 *    version: 1.0
 */
class WechatModel: BaseViewModel() {
    private val repository by lazy {
        HomeRepository()
    }

    val wxArticleData = MutableLiveData<MutableList<Wechat>>()

    /**
     * 发起请求
     */
    fun getWXArticle(){
        launch {
            wxArticleData.value = repository.getWXChapters().data()
        }
    }

}
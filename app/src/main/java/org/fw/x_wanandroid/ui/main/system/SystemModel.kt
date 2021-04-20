package org.fw.x_wanandroid.ui.main.system

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
class SystemModel: BaseViewModel() {
    private val repository by lazy {
        HomeRepository()
    }

    val articleData = MutableLiveData<Article>()

    /**
     * 发起请求
     */
    fun getPlazaList(page: Int){
        launch {
            articleData.value = repository.getPlazaList(page).data()
        }
    }

}
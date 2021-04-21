package org.fw.x_wanandroid.ui.main.system

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fw.base_library.base.BaseViewModel
import org.fw.x_wanandroid.bean.*
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

    val knowledgeTreeData = MutableLiveData<MutableList<KnowledgeTree>>()
    val navigationData = MutableLiveData<MutableList<NavigationBean>>()

    /**
     * 发起请求
     */
    fun getKnowledgeTree(){
        launch {
            knowledgeTreeData.value = repository.getKnowledgeTree().data()
        }
    }

    fun getNavigationList(){
        launch {
            navigationData.value = repository.getNavigationList().data()
        }
    }

}
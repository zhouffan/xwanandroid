package com.fw.base_library.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/9 22:36
 *    desc   :
 *    version: 1.0
 */
abstract class BaseVmFragment<VB: ViewBinding, VM: ViewModel>: BaseFragment<VB>() {
    //当前页数
    var page: Int = 0
    //总页数
    var pageCount: Int = 1
    //加载状态
    var loadArticleState: Int = -1

    lateinit var mViewModel: VM
    private var lazyLoad = false

    override fun initialize() {
        super.initialize()
        mViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)).get(getViewModelClass())

        observe()
        init()
    }

    override fun onResume() {
        super.onResume()
        if(!lazyLoad){
            lazyLoadData()
            lazyLoad = true
        }
    }

    abstract fun observe()

    abstract fun init()

    /**
     * 懒加载数据
     */
    abstract fun lazyLoadData()

    abstract fun getViewModelClass(): Class<VM>
}
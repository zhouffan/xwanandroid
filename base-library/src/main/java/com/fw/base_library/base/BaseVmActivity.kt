package com.fw.base_library.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.fw.base_library.util.ToastUtil

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/8 22:42
 *    desc   :
 *    version: 1.0
 */
abstract class BaseVmActivity<VB:ViewBinding, VM: ViewModel> : BaseActivity<VB>(){
    open lateinit var viewModel: VM

    override fun initialize() {
        super.initialize()
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(getViewModelClass());
        observe()
        init()
    }

    abstract fun getViewModelClass(): Class<VM>

    open fun observe(){}

    open fun init(){}

    override fun onDestroy() {
        super.onDestroy()

    }
}
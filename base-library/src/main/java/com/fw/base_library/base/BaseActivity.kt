package com.fw.base_library.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.fw.base_library.util.ToastUtil

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/8 22:22
 *    desc   : 基类
 *    version: 1.0
 */
abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity(){
    open lateinit var mBinding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = getViewBinding()
        setContentView(mBinding.root)

        initialize()
    }

    abstract fun getViewBinding(): VB


    open fun initialize(){

    }



    override fun onDestroy() {
        super.onDestroy()
        ToastUtil.cancel()
    }

}
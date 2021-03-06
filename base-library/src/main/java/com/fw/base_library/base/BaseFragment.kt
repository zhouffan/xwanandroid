package com.fw.base_library.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/9 22:29
 *    desc   :
 *    version: 1.0
 */
abstract class BaseFragment<VB: ViewBinding>: Fragment(){
    lateinit var fragment: Fragment
    lateinit var mActivity: FragmentActivity

    lateinit var mViewBinding: VB

    abstract fun getViewBinding(): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = getViewBinding()
        mActivity = this.activity!!
        fragment = this
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    open fun initialize(){

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
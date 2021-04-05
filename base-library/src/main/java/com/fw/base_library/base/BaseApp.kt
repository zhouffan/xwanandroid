package com.fw.base_library.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/3/31 23:22
 *    desc   :
 *    version: 1.0
 */
class BaseApp : Application(){
    init {
        initSmartRefreshLayout()
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        registerActivityLifecycle()
    }

    companion object{
        lateinit var application: Application

    }

    /**
     * SmartRefreshLayout默认效果，会被覆盖
     */
    private fun initSmartRefreshLayout(){
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(android.R.color.holo_blue_dark, android.R.color.holo_red_dark, android.R.color.holo_orange_dark)
            ClassicsHeader(context)
        }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            ClassicsFooter(context)
        }
    }

    /**
     * 注册页面监听
     */
    private fun registerActivityLifecycle(){
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks{
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityDestroyed(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivityCreated(activity: Activity, p1: Bundle?) {}
            override fun onActivityResumed(activity: Activity) {}

        })
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}
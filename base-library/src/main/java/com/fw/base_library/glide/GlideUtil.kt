package com.fw.base_library.glide

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/13 21:52
 *    desc   : https://muyangmin.github.io/glide-docs-cn/doc/getting-started.html
 *    图片加载
 *    version: 1.0
 */
object GlideUtil {
    private val options: RequestOptions by lazy {
        RequestOptions().let {
            it.centerCrop()
            //当请求成功完成时，占位符会被请求到的资源替换
            it.placeholder(ColorDrawable(Color.BLACK))
            //请求永久性失败时展示
            it.error(ColorDrawable(Color.RED))
            //允许用户指示 null 是否为可接受的正常情况
            it.fallback(ColorDrawable(Color.BLUE))
            it.diskCacheStrategy(DiskCacheStrategy.ALL)
            it
        }

    }

    fun demo(fragment: Fragment, url: String, iv:ImageView){
        GlideApp.with(fragment)
            .load(url)
            .apply(options)
                //交叉淡入变换
            .transition(withCrossFade())
                //不在缓存中，则加载失败     ===> 省流量模式
//            .onlyRetrieveFromCache(true)
                //请求跳过磁盘和/或内存缓存  ===>  图片验证码
//            .skipMemoryCache(true) //仅跳过内存
//            .diskCacheStrategy(DiskCacheStrategy.NONE) //仅跳过磁盘
            .into(iv)
    }

    /**
     * 加载图片
     * @param fragment Fragment
     * @param url String
     * @param iv ImageView
     */
    fun show(fragment: Fragment, url: String, iv:ImageView){
        GlideApp.with(fragment)
            .load(url)
            .apply(options)
            //交叉淡入变换
            .transition(withCrossFade())
            .into(iv)
    }

    fun show(activity: Activity, url: String, iv:ImageView){
        GlideApp.with(activity)
            .load(url)
            .apply(options)
            //交叉淡入变换
            .transition(withCrossFade())
            .into(iv)
    }

    fun show(context: Context, url: String, iv:ImageView){
        GlideApp.with(context)
            .load(url)
            .apply(options)
            //交叉淡入变换
            .transition(withCrossFade())
            .into(iv)
    }

    /**
     * 恢复加载图片
     * @param fragment Fragment
     */
    fun resumeRequests(fragment: Fragment){
        GlideApp.with(fragment).resumeRequests()
    }

    /**
     * 暂停加载图片
     * @param fragment Fragment
     */
    fun pauseAllRequests(fragment: Fragment){
        GlideApp.with(fragment).pauseAllRequests()
    }



    /**
     * 清理所有内存并非特别经济,  出现抖动和增加加载时间
     * @param context Context
     */
    fun clearMemory(context: Context){
        GlideApp.get(context).clearMemory()
    }

    /**
     * 清理所有磁盘缓存条目
     * @param context Context
     */
    fun clearDiskCache(context: Context){
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        coroutineScope.launch {
            GlideApp.get(context).clearDiskCache()
        }
    }

}
package com.fw.base_library.glide

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.executor.GlideExecutor
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/13 21:54
 *    desc   :
 *    version: 1.0
 */
@GlideModule
class MyAppGlideModule: AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)

        //内存缓存 => 使用固定大小的内存和 LRU 算法
        val memoryCacheSizeByte: Long = 1024 * 1024 * 20; // 20mb
        builder.setMemoryCache(LruResourceCache(memoryCacheSizeByte))

        //Bitmap池 => LruBitmapPool 是一个内存中的固定大小的 BitmapPool，使用 LRU 算法清理。默认大小基于设备的分辨率和密度
        val bitmapPoolSize:Long = 1024 * 1024 * 30; // 30mb
        builder.setBitmapPool(LruBitmapPool(bitmapPoolSize))

        //磁盘缓存 => Glide 使用 DiskLruCacheWrapper 作为默认的 磁盘缓存 。 DiskLruCacheWrapper 是一个使用 LRU 算法的固定大小的磁盘缓存。
        // 默认磁盘大小为 250 MB ，位置是在应用的 缓存文件夹 中的一个 特定目录
        val diskCacheSizeBytes:Long = 1024 * 1024 * 100; // 30mb
        val diskCacheName = "x-android"
//        ExternalCacheDiskCacheFactory(context)  外部存储
        builder.setDiskCache(InternalCacheDiskCacheFactory(context,
            diskCacheName, diskCacheSizeBytes))

        //统一配置
        builder.setDefaultRequestOptions(
            RequestOptions()
                .format(DecodeFormat.PREFER_RGB_565)
                    //硬件位图: Bitmap.Config.HARDWARE 是一种 Android O 添加的新的位图格式。硬件位图仅在显存 (graphic memory) 里存储像素数据，并对图片仅在屏幕上绘制的场景做了优化。
                    //默认加载硬件位图而不需要额外的启用配置，只保留禁用的选项
//                .disallowHardwareConfig()
                    // AUTOMATIC: 当加载远程数据时，磁盘仅会存储原始数据。对于本地数据，仅存储变换过的缩略图
                    // NONE 不使用磁盘缓存
                    // DATA 在资源解码前就将原始数据写入磁盘缓存
                    // RESOURCE 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
                    // ALL 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        )
        //日志级别
        builder.setLogLevel(Log.WARN)

        //异常捕获 => 可以传入一个磁盘执行器和/或一个 resize 执行器
//        val strategy = GlideExecutor.UncaughtThrowableStrategy(){
//
//        }
//        builder.setDiskCacheExecutor(GlideExecutor.newDiskCacheExecutor(strategy))
    }
}
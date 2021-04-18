package org.fw.x_wanandroid

import com.fw.base_library.base.BaseApp
import com.fw.base_library.net.RetrofitUtil

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/10 17:11
 *    desc   :
 *    version: 1.0
 */
class MyApp : BaseApp(){
    override fun onCreate() {
        super.onCreate()

        RetrofitUtil.setBaseUrl(API.BASE_URL)

    }


}
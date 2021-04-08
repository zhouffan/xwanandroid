package com.fw.base_library.util

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.widget.Toast

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/8 20:54
 *    desc   : toast提示 - 主线程/子线程
 *    version: 1.0
 */

object ToastUtil {
    private var toast: Toast? = null

    /**
     * toast提示
     * @param context Context 如果为activity 会判断是否是在主线程中
     * @param msg String
     * @param center Boolean
     */
    fun show(context: Context, msg: String, center: Boolean = false){
        if("main" == Thread.currentThread().name){
            createToast(context, msg, center)
        }else{
            if(context is Activity){
                context.runOnUiThread {
                    createToast(context, msg, center)
                }
            }else{

            }
        }
    }

    private fun createToast(context: Context, msg: String, center: Boolean){
        toast?.cancel()
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        toast?.apply {
            setText(msg)
            //居中
            if (center) {
                setGravity(Gravity.CENTER, 0, 0)
            }
            show()
        }
    }

    /**
     * 取消
     */
    fun cancel(){
        toast?.let {
            it.cancel()
            toast = null
        }
    }
}
package com.fw.base_library.util

import android.util.Log

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/8 21:49
 *    desc   : 日志打印
 *    version: 1.0
 */
object LogUtil {
    private const val TAG = "LogUtil==>"
    private var SHOW = true
    private const val MAX_LENGTH = 400

    fun setHide(){
        SHOW = false
    }

    fun v(msg: String) {
        log('v', msg)
    }

    fun d(msg: String) {
        log('d', msg)
    }

    fun i(msg: String) {
        log('i', msg)
    }

    fun w(msg: String) {
        log('w', msg)
    }

    fun e(msg: String) {
        log('e', msg)
    }

    /**
     * 数据太多，则换行打印
     * @param tag Char
     * @param msg String
     */
    private fun log( tag: Char, msg: String) {
        if (SHOW) {
            val info = autoJumpLogInfos
            val msgLength = msg.length
            var start = 0
            var end = MAX_LENGTH
            //循环多次
            for (i in 0..99) {
                //当行打印大于MAX_LENGTH 时，分行打印
                if (msgLength > end) {
                    val str = info[1] + info[2] + " --->> " + msg.substring(start, end)
                    print(tag, str)
                    start = end
                    //下一行接着打印
                    end += MAX_LENGTH
                } else {
                    val str = info[1] + info[2] + " --->> " + msg.substring(start, msgLength)
                    print(tag, str)
                    //会提前终止循环
                    break
                }
            }
        }
    }

    /**
     * 打印
     * @param tag Char
     * @param str String
     */
    private fun print(tag: Char = 'i', str: String){
        when (tag) {
            'i' -> Log.i(TAG, str)
            'd' -> Log.d(TAG, str)
            'v' -> Log.v(TAG, str)
            'w' -> Log.w(TAG, str)
            'e' -> Log.e(TAG, str)
        }
    }
    /**
     * 获取打印信息所在方法名，行号等信息
     */
    private val autoJumpLogInfos: Array<String>
        get() {
            val infos = arrayOf("", "", "")
            val elements = Thread.currentThread().stackTrace
            infos[0] = elements[4].className.substring(elements[4].className.lastIndexOf(".") + 1)
            infos[1] = elements[4].methodName
            infos[2] = "(" + elements[4].fileName + ":" + elements[4].lineNumber + ")"
            return infos
        }

}
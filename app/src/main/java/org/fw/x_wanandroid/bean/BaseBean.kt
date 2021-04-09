package org.fw.x_wanandroid.bean

import com.fw.base_library.net.ApiException


/**
 * Created by yechaoa on 2020/2/4.
 * Describe :
 */
class BaseBean<T>(private val errorCode: Int, private val data: T, private val errorMsg: String?) {

    fun code(): Int {
        if (errorCode == 0) {
            return errorCode
        } else {
            throw ApiException(errorCode, errorMsg ?: "")
        }
    }

    fun data(): T {
        if (errorCode == 0) {
            return data
        } else {
            throw ApiException(errorCode, errorMsg ?: "")
        }
    }

}
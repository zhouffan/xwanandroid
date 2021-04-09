package com.fw.base_library.net

import java.lang.RuntimeException

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/9 23:20
 *    desc   :
 *    version: 1.0
 */
class ApiException(var code: Int, message: String): RuntimeException(message) {
}
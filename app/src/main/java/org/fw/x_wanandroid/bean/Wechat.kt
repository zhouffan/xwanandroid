package org.fw.x_wanandroid.bean

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/20 21:21
 *    desc   :
 *    version: 1.0
 */
data class Wechat(
    val children: List<String>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)
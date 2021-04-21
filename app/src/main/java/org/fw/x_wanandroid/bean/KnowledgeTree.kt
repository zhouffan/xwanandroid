package org.fw.x_wanandroid.bean

import java.io.Serializable

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/21 21:35
 *    desc   :
 *    version: 1.0
 */
data class KnowledgeTree(
    val children: List<Wechat>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
): Serializable

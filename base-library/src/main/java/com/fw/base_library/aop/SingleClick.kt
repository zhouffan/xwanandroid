package com.fw.base_library.aop

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/3 10:57
 *    desc   :
 *
 *    https://www.jianshu.com/p/8105c014995c
 *    https://www.jianshu.com/p/3b94562347ef
 *
 *
 *
 *    重点：注意：如果aspectjx是在module或者library中引入依赖，
 *    则在app的build.gradle也需要添加依赖。 否则注解无效
 *
 *
    1、定义注解
    2、定义切面
    3、定义切点
    4、关联切点及逻辑处理

 *    version: 1.0
 */

/**
 * 第一步定义注解（这就是用来修饰需要 xx 方法的注解）
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class SingleClick(val value: Long  = 1000)
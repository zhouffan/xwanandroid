package com.fw.base_library.aop

import android.util.Log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import java.lang.StringBuilder

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/3 11:06
 *    desc   :
 *    第二步定义切面使用@Aspect修饰一个类
 *
 *    version: 1.0
 */

@Aspect
class SingleClickAspect {
    private val TAG = "SingleClickAspect===>"
    /** 最近一次点击的时间 */
    private var mLastTime: Long = 0
    /** 最近一次点击的标记 */
    private var mLastTag = ""

    /**
     * 方法切入点
     *
     * 第三步定义切点 使用@Pointcut修饰一个用@Aspect修饰的类中的方法
     * @Pointcut 中execution的参数表示的是所有带PrintFunTime注解的方法
        此处由于只是一个简单的示例所以只用了这一种方式，若需要适配更多的方法可以参考
        [https://blog.csdn.net/zlmrche/article/details/79643801](https://blog.csdn.net/zlmrche/article/details/79643801)
        这里面有更详细的说明
     */
    @Pointcut("execution(@com.fw.base_library.aop.SingleClick * *(..))")
    fun method(){}

    /**
     * 第四步关联切点与处理逻辑方法 使用@Around修饰定义的方法，参数为第三步的切点方法
     */
    @Around("method() && @annotation(singleClick)")
    @Throws(Throwable::class)
    fun aroundJoinPoint(joinPoint: ProceedingJoinPoint, singleClick: SingleClick){
        val signature = joinPoint.signature
        val className = signature.declaringType.name
        val methodName = signature.name
        val sb = StringBuilder("$className.$methodName")
        sb.append("(")
        val args = joinPoint.args
        for ((index, arg) in args.withIndex()){
            if(index == 0){
                sb.append(arg)
            }else{
                sb.append(",").append(arg)
            }
        }
        sb.append(")")
        val tag = sb.toString()
        val currentTimeMillis = System.currentTimeMillis()
        if(currentTimeMillis - mLastTime < singleClick.value && tag.equals(mLastTag)){
            Log.i(TAG, "${singleClick.value}毫秒内快速点击：$tag");
            return
        }
        mLastTime = currentTimeMillis
        mLastTag = tag
        //执行原方法
        joinPoint.proceed()
    }

}
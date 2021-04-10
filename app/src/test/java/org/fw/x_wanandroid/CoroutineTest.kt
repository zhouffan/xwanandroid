package org.fw.x_wanandroid

import kotlinx.coroutines.*
import org.junit.Test

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/10 11:56
 *    desc   :
 *
 *    GlobalScope.launch{} 创建一个顶级协程 //不阻塞当前线程  --不用
 *    runBlocking{} 创建一个协程作用域      //阻塞当前线程    --不用
 *    launch{} 在协程作用域内创建一个协程
 *    coroutineScope{} 在协程作用域内创建一个子协程作用域       //阻塞当前协程  ---推荐
 *    async{}.await() 代码块中的代码会立刻执行，当调用await()时，会阻塞当前协程，直到获取结果
 *    withContext(Dispatchers.Default){} 代码块会立即执行，同时阻塞协程，直到获取结果
 *
 *    version: 1.0
 */
class CoroutineTest {
    @Test
    fun addition_isCorrect() {
        createCoroutine()

        Thread.sleep(1000)
    }

    /**
     * 创建协程方式
     */
    fun createCoroutine(){
        println("0:"+Thread.currentThread())
        //方式一    不阻塞
        GlobalScope.launch{
            println("1:"+Thread.currentThread())
        }
        GlobalScope.launch(Dispatchers.IO) {
            println("2:"+Thread.currentThread())
        }
        //
        Thread{
            println("3:"+Thread.currentThread())
        }.start()
//        0:Thread[main,5,main]
//        1:Thread[DefaultDispatcher-worker-1 @coroutine#1,5,main]
//        2:Thread[DefaultDispatcher-worker-2 @coroutine#2,5,main]
//        3:Thread[Thread-3,5,main]
    }

    /**

     */
    fun testCoroutine(){
        println("1:"+Thread.currentThread())
//        GlobalScope.launch {
//            println("2:"+Thread.currentThread())
//        }

        runBlocking {
            println("3:"+Thread.currentThread())
            launch {
                println("4:"+Thread.currentThread())
            }
            coroutineScope {
                println("5:"+Thread.currentThread())
//                withContext(Dispatchers.Main){
//                    println("6:"+Thread.currentThread())
//                }
                println("7:"+Thread.currentThread())
            }
        }
        println("1:=====>end")
    }
}
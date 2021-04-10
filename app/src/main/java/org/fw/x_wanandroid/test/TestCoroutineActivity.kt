package org.fw.x_wanandroid.test

import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import com.fw.base_library.base.BaseVmActivity
import com.fw.base_library.net.RetrofitUtil
import com.fw.base_library.util.LogUtil
import com.fw.base_library.util.ToastUtil
import kotlinx.coroutines.*
import org.fw.x_wanandroid.API
import org.fw.x_wanandroid.R
import org.fw.x_wanandroid.databinding.ActivityTest2Binding
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/10 12:04
 *    desc   :
 *
 *    导入包：implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2-native-mt"
 *    GlobalScope.launch{} 创建一个顶级协程   //不阻塞当前线程   --不用
 *    runBlocking{}        创建一个协程作用域  //阻塞当前线程    --不用
 *    CoroutineScope(Dispatchers.Main).launch{xxx} 创建协程   --推荐
 *    launch{} 在协程作用域内创建一个协程
 *    coroutineScope{} 在协程作用域内创建一个子协程作用域       //阻塞当前协程
 *    async{}.await() 代码块中的代码会立刻执行，当调用await()时，会阻塞当前协程，直到获取结果
 *    withContext(Dispatchers.Default){} 代码块会立即执行，同时阻塞协程，直到获取结果
 *    suspendCoroutine{continuation -> } 必须在挂起函数或协程作用域中才可调用，将当前协程挂起，然后在普通线程中执行lambda表达式中的代码，再调用resume() 或 resumeWithException(e)让协程恢复
 *
 *    version: 1.0
 */

class TestCoroutineActivity : BaseVmActivity<ActivityTest2Binding, TestViewModel>() {
//    lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_test2)
//        btn = findViewById<Button>(R.id.btn)

        /**
         * 子线程真的不能更新 UI?
         * ==>在onCreate中启动一个线程，在线程中能更新UI，为什么？（线程中延时 或者 在点击触发，则会抛出异常）
         * ==>答疑：异常是从 ViewRootImpl#checkThread() 方法中抛出。 在 onCreate 完成时，ViewRootImpl 还没创建。
         * Activity 并没有完成初始化 view tree
         */
        mViewBinding.btn.setOnClickListener {
            //对比方式1：传统方式， 如果多次请求，且有先后顺序怎么办？嵌套？
            Thread{
                LogUtil.i("3 ${Thread.currentThread()}")
                var message = getMessageFromNetwork()  //回调 先后
                var message1 = getMessageFromNetwork() //回调 先后
                var message3 = getMessageFromNetwork() //回调 先后
                //UI
                runOnUiThread {
                    mViewBinding.btn.text = message
                }
            }.start()

            //对比方式2：协程方式
            GlobalScope.launch(Dispatchers.Main) {
                LogUtil.i("1 ${Thread.currentThread().name}")
                val message = getMessageFromNetwork1()
                try {
                    val message1 = getMessageFromNetwork1()
                    val message2 = getMessageFromNetwork1()

                    /**
                     * 需求：两个接口都请求完毕的时 显示出结果
                     * async函数必须在协程作用域中调用，会创建一个新的 子协程 ，并返回一个Deferred对象，
                     * 调用这个对象的await方法 就可以获取执行结果
                     */
                    val message3 = async { getMessageFromNetwork1() }
                    val message4 = async { getMessageFromNetwork1() }
                    val m3 = message3.await()
                    val m4 = message4.await()

                } catch (e: Exception) {
                    //等同于callback的 onFailure的回调
                }
                mViewBinding.btn.text = message
            }

            //推荐协程写法
            val coroutineScope = CoroutineScope(Dispatchers.Main)
            coroutineScope.launch{
                val message1 = getMessageFromNetwork1()
                val apiService = RetrofitUtil.getApiService(API::class.java)
                val banner = apiService.getBanner().data()
                LogUtil.i(message1)
            }
            coroutineScope.cancel()
        }

        mViewBinding.btn2.setOnClickListener {
            mViewModel.getBanner()
        }
    }

    /**
     * 传统写法
     */
    private fun getMessageFromNetwork(): String {
        for (i in 0..1000000) {
            //这里模拟一个耗时操作
        }
        val name = "xxxxxxxx"
        return name
    }

    /**
     * 当运行到挂起函数的时候，协程会处于等待状态，等返回结果后，主动切回主线程---阻塞
     *
     * 1、协程可以帮我们自动切线程
     * 2、摆脱了链式回调的问题
     */
    private suspend fun getMessageFromNetwork1(): String {
        var name = ""
        //代码块会立即执行，同时阻塞协程，直到获取结果
        withContext(Dispatchers.IO){
            for (i in 0..1000000) {
                //这里模拟一个耗时操作
            }
            name = "ooooooo"
        }

        return name
    }

    /**
     * 必须在挂起函数或协程作用域中才可调用，将当前协程挂起，
     * 然后在普通线程中执行lambda表达式中的代码，再调用resume() 或 resumeWithException(e)让协程恢复
     * @param param Int
     * @return String
     */
    suspend fun request(param: Int): String {
        return suspendCoroutine { c ->
            val num = (1..20).random()
            if (param > num) {
                c.resume("成功 $param > $num")
            } else {
                c.resumeWithException(Exception("失败$param <= $num"))
            }
        }
    }

    override fun getViewBinding(): ActivityTest2Binding {
        return ActivityTest2Binding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): Class<TestViewModel> {
        return TestViewModel::class.java
    }

    override fun observe() {
        super.observe()
        mViewModel.bannerData.observe(this, Observer {
            ToastUtil.show(mActivity, it[0].toString())
            mViewBinding.btn2.text = it[0].desc
        })
    }
}
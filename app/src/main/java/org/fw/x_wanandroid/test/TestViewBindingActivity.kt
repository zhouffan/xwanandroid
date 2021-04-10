package org.fw.x_wanandroid.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fw.base_library.net.RetrofitUtil
import com.fw.base_library.util.LogUtil
import com.fw.base_library.util.ToastUtil
import kotlinx.coroutines.*
import org.fw.x_wanandroid.API
import org.fw.x_wanandroid.bean.Banner
import org.fw.x_wanandroid.bean.BaseBean
import org.fw.x_wanandroid.databinding.ActivityTest3Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestViewBindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding = ActivityTest3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var i = 0
        LogUtil.i("ssssssss")
        LogUtil.v("ssssssss")
        LogUtil.d("ssssssss")
        LogUtil.w("ssssssss")
        LogUtil.e("ssssssss")
        //test viewmodel
        var viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(TestViewModel::class.java);


        binding.btn.setOnClickListener {
            Thread{
                i++
                ToastUtil.show(this@TestViewBindingActivity, "==>$i")
            }.start()
            viewModel.add(10)
        }

        viewModel.num.observe(this, Observer{
            binding.btn.text = "==>$i"
        })


        testHttp()
    }

    /**
     * 请求的方式
     */
    private fun testHttp(){
        //对比方式1： 挂起函数，返回实体
        runBlocking {
            val apiService = RetrofitUtil.getApiService(API::class.java)
            val banner = apiService.getBanner()
            LogUtil.i(""+banner)
        }

        //对比方式2： 返回callback
        val apiService = RetrofitUtil.getApiService(API::class.java)
        val bannerCall = apiService.getBanner2()
        bannerCall.enqueue(object : Callback<BaseBean<MutableList<Banner>>> {
            override fun onFailure(call: Call<BaseBean<MutableList<Banner>>>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<BaseBean<MutableList<Banner>>>,
                response: Response<BaseBean<MutableList<Banner>>>
            ) {
                val banner = response.body()?.data()
                LogUtil.i(""+banner)
                val desc = banner!![0]?.desc
            }
        })
    }


}
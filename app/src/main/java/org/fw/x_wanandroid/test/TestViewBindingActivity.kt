package org.fw.x_wanandroid.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fw.base_library.glide.GlideUtil
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

        GlideUtil.show(this@TestViewBindingActivity,
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Ff1f91553d69196980d9f0cc05dec92393192c8c31f9d7-LcE79T_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1620919727&t=e3448964ce1aa4e59024d4b26aafcec1", binding.iv)

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
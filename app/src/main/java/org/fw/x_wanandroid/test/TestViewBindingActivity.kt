package org.fw.x_wanandroid.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fw.base_library.net.RetrofitClient
import com.fw.base_library.util.LogUtil
import com.fw.base_library.util.ToastUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.fw.x_wanandroid.API
import org.fw.x_wanandroid.R
import org.fw.x_wanandroid.databinding.ActivityTest3Binding

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


        //test http

        runBlocking {
            val apiService = RetrofitClient.getApiService(API::class.java, API.BASE_URL)
            val banner = apiService.getBanner()
            LogUtil.i(""+banner)
        }
    }


}
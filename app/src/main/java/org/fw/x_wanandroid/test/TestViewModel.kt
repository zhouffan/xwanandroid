package org.fw.x_wanandroid.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/8 22:46
 *    desc   :
 *    version: 1.0
 */
class TestViewModel: ViewModel() {
    val num: MutableLiveData<Int> = MutableLiveData()

    fun add(value: Int){
        num.value = num.value?.plus(value)
    }

}
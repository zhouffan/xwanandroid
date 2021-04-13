package org.fw.x_wanandroid.ui.main.home

import android.R.attr.banner
import androidx.fragment.app.Fragment
import com.fw.base_library.base.BaseVmFragment
import com.fw.base_library.glide.GlideUtil
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import org.fw.x_wanandroid.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseVmFragment<FragmentHomeBinding, HomeViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun init() {
        //--------------------------简单使用-------------------------------
//        mViewBinding.banner.addBannerLifecycleObserver(this) //添加生命周期观察者
//            .setAdapter(BannerExampleAdapter(DataBean.getTestData()))
//            .setIndicator(CircleIndicator(this))
//
//        //—————————————————————————如果你想偷懒，而又只是图片轮播————————————————————————

        //—————————————————————————如果你想偷懒，而又只是图片轮播————————————————————————
        val imgs = mutableListOf<String>("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Ff1f91553d69196980d9f0cc05dec92393192c8c31f9d7-LcE79T_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1620919727&t=e3448964ce1aa4e59024d4b26aafcec1",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Ff1f91553d69196980d9f0cc05dec92393192c8c31f9d7-LcE79T_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1620919727&t=e3448964ce1aa4e59024d4b26aafcec1",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Ff1f91553d69196980d9f0cc05dec92393192c8c31f9d7-LcE79T_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1620919727&t=e3448964ce1aa4e59024d4b26aafcec1")
        mViewBinding.banner.setAdapter(object : BannerImageAdapter<String>(imgs) {
            override fun onBindView(
                holder: BannerImageHolder,
                data: String,
                position: Int,
                size: Int
            ) {
                //图片加载自己实现
                GlideUtil.show(this@HomeFragment, data, holder.imageView)
            }
        })
            .addBannerLifecycleObserver(this) //添加生命周期观察者
    }

    override fun observe() {

    }

    override fun lazyLoadData() {

    }
}
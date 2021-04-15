package org.fw.x_wanandroid.ui.main.home

import android.content.res.ColorStateList
import android.graphics.Color
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.fw.base_library.base.BaseVmFragment
import com.fw.base_library.glide.GlideUtil
import com.fw.base_library.util.LogUtil
import com.fw.base_library.util.ToastUtil
import com.fw.base_library.util.px2dp
import com.fw.base_library.util.px2sp
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.transformer.*
import org.fw.x_wanandroid.R
import org.fw.x_wanandroid.bean.Banner
import org.fw.x_wanandroid.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseVmFragment<FragmentHomeBinding, HomeViewModel>() {
    var page: Int = 0

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
        val imgs = mutableListOf<String>("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Ff1f91553d69196980d9f0cc05dec92393192c8c31f9d7-LcE79T_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1620919727&t=e3448964ce1aa4e59024d4b26aafcec1",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Ff1f91553d69196980d9f0cc05dec92393192c8c31f9d7-LcE79T_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1620919727&t=e3448964ce1aa4e59024d4b26aafcec1",
        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Ff1f91553d69196980d9f0cc05dec92393192c8c31f9d7-LcE79T_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1620919727&t=e3448964ce1aa4e59024d4b26aafcec1")
        mViewBinding.banner.let {
            it.addBannerLifecycleObserver(this) //添加生命周期观察者
            it.indicator = CircleIndicator(activity)
            it.setPageTransformer(ZoomOutPageTransformer())
        }
    }

    override fun observe() {
        mViewModel.bannerData.observe(this){
            mViewBinding.banner.adapter = ImageAdapter(this@HomeFragment, it)
        }
        mViewModel.articleData.observe(this){
            ToastUtil.show(fragment.requireContext(), ""+it.total)
        }
    }

    override fun lazyLoadData() {
        mViewModel.getBannerData()
        mViewModel.getArticleData(page)
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewBinding.banner.destroy()
    }

    override fun onStart() {
        super.onStart()
        mViewBinding.banner.start()
    }

    override fun onStop() {
        super.onStop()
        mViewBinding.banner.stop()
    }

    class ImageAdapter(val fragment: Fragment, data: MutableList<Banner>):
        BannerAdapter<Banner, ImageAdapter.BannerViewHolder>(data){
        class BannerViewHolder(val img: ImageView, val txt: TextView, view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
            val home = RelativeLayout(parent.context)
            home.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            val img = ImageView(home.context)
            img.layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            home.addView(img)
            val txt = TextView(home.context)
            txt.textSize = px2sp(45f)
            txt.setSingleLine()
            txt.ellipsize = TextUtils.TruncateAt.END
            txt.setTextColor(ContextCompat.getColor(home.context, R.color.white))
            txt.setBackgroundColor(ContextCompat.getColor(home.context, R.color.DCDCDC))
            txt.setPadding(px2dp(60),px2dp(60),
                px2dp(800),px2dp(60))
            val layoutParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//            layoutParams.setMargins(50,0,0,0)
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
            txt.layoutParams = layoutParams
            home.addView(txt)
            return BannerViewHolder(img, txt, home)
        }

        override fun onBindView(
            holder: BannerViewHolder,
            data: Banner,
            position: Int,
            size: Int
        ) {
            LogUtil.i(data.imagePath)
            //图片加载自己实现
            GlideUtil.show(fragment, data.imagePath, holder.img)
            holder.txt.text = data.title
        }
    }
}
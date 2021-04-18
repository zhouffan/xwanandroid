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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fw.base_library.base.BaseVmFragment
import com.fw.base_library.glide.GlideUtil
import com.fw.base_library.util.*
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.transformer.*
import org.fw.x_wanandroid.R
import org.fw.x_wanandroid.bean.Banner
import org.fw.x_wanandroid.bean.Data
import org.fw.x_wanandroid.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseVmFragment<FragmentHomeBinding, HomeViewModel>() {
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var bannerAdapter: ImageAdapter

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
            bannerAdapter = ImageAdapter(this@HomeFragment, mutableListOf())
            it.adapter = bannerAdapter
        }

//        mViewBinding.homeRv.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        context?.let { mViewBinding.homeRv.addItemDecoration(SpaceItemDecoration(it))}
        homeAdapter = HomeAdapter(mutableListOf())
        mViewBinding.homeRv.adapter = homeAdapter

        mViewBinding.refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener{
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page++
                if(page >= pageCount){
                    page = pageCount - 1
                }
                loadArticleState = 0
                mViewModel.getAllArticleData(page)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 0
                loadArticleState = 1
                mViewModel.getAllArticleData(page);
            }

        })

    }

    override fun observe() {
        mViewModel.bannerData.observe(this){
            bannerAdapter.setDatas(it)
            bannerAdapter.notifyDataSetChanged()
        }
        mViewModel.articleData.observe(this){
//            ToastUtil.show(fragment.requireContext(), ""+it.datas)
            this.pageCount = it.pageCount
            homeAdapter.addData(it.datas)

            when (loadArticleState) {
                0 -> mViewBinding.refreshLayout.finishLoadMore()
                1 -> mViewBinding.refreshLayout.finishRefresh()
            }
        }
    }

    override fun lazyLoadData() {
        page = 0
        mViewModel.getBannerData()
        mViewModel.getAllArticleData(page)
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


}
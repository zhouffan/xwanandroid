package org.fw.x_wanandroid.ui.main.plaza

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.fw.base_library.base.BaseVmFragment
import com.fw.base_library.util.SpaceItemDecoration
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import org.fw.x_wanandroid.R
import org.fw.x_wanandroid.databinding.FragmentPlazaBinding
import org.fw.x_wanandroid.ui.main.home.HomeAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [PlazaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlazaFragment : BaseVmFragment<FragmentPlazaBinding, PlazaModel>() {

    companion object {
        @JvmStatic
        fun newInstance() = PlazaFragment()
    }

    override fun observe() {
        mViewModel.articleData.observe(this){
            mViewBinding.recyclerView.adapter = HomeAdapter(it.datas)
            this.pageCount = it.pageCount

            when (loadArticleState) {
                0 -> mViewBinding.refreshLayout.finishLoadMore()
                1 -> mViewBinding.refreshLayout.finishRefresh()
            }
        }
    }

    override fun init() {
        context?.let { mViewBinding.recyclerView.addItemDecoration(SpaceItemDecoration(it))}
        mViewBinding.refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page++
                if(page >= pageCount){
                    page = pageCount - 1
                }
                loadArticleState = 0
                mViewModel.getPlazaList(page)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 0
                loadArticleState = 1
                mViewModel.getPlazaList(page);
            }

        })
    }

    override fun lazyLoadData() {
        page = 0
        mViewModel.getPlazaList(page);
    }

    override fun getViewModelClass() = PlazaModel::class.java

    override fun getViewBinding() = FragmentPlazaBinding.inflate(layoutInflater)
}
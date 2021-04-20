package org.fw.x_wanandroid.ui.main.wechat

import android.os.Bundle
import androidx.lifecycle.observe
import com.fw.base_library.base.BaseVmFragment
import com.fw.base_library.util.SpaceItemDecoration
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import org.fw.x_wanandroid.databinding.FragmentPersonalArticleBinding
import org.fw.x_wanandroid.ui.main.home.HomeAdapter

const val CONTENT_CID_KEY = "cid"
class PersonalArticleFragment : BaseVmFragment<FragmentPersonalArticleBinding, PersonalArticleModel>() {
    private var cid: Int = 0
    companion object {
        @JvmStatic
        fun newInstance(cid: Int) =PersonalArticleFragment().apply {
            arguments = Bundle().apply {
                putInt(CONTENT_CID_KEY, cid)
            }
        }

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
        cid = arguments?.getInt(CONTENT_CID_KEY)?:0

        context?.let { mViewBinding.recyclerView.addItemDecoration(SpaceItemDecoration(it))}
        mViewBinding.refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page++
                if(page >= pageCount){
                    page = pageCount - 1
                }
                loadArticleState = 0
                mViewModel.getKnowledgeList(page, cid)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 0
                loadArticleState = 1
                mViewModel.getKnowledgeList(page, cid)
            }

        })
    }

    override fun lazyLoadData() {
        page = 0
        mViewModel.getKnowledgeList(page, cid)
    }

    override fun getViewModelClass(): Class<PersonalArticleModel> = PersonalArticleModel::class.java

    override fun getViewBinding(): FragmentPersonalArticleBinding =
        FragmentPersonalArticleBinding.inflate(layoutInflater)
}
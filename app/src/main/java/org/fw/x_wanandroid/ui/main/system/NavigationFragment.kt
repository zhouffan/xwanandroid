package org.fw.x_wanandroid.ui.main.system

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.fw.base_library.base.BaseVmFragment
import org.fw.x_wanandroid.R
import org.fw.x_wanandroid.bean.NavigationBean
import org.fw.x_wanandroid.databinding.FragmentNavigationBinding
import q.rorbin.verticaltablayout.adapter.TabAdapter
import q.rorbin.verticaltablayout.widget.ITabView

class NavigationFragment : BaseVmFragment<FragmentNavigationBinding, SystemModel>() {

    companion object {
        @JvmStatic
        fun newInstance() = NavigationFragment()
    }

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    override fun observe() {
        mViewModel.navigationData.observe(this){
            setLeft(it)
            setRight(it)
        }
    }

    fun setLeft(data: MutableList<NavigationBean>){
        mViewBinding.navigationTabLayout.setTabAdapter(object: TabAdapter{
            override fun getIcon(position: Int): ITabView.TabIcon? {
                return null
            }

            override fun getBadge(position: Int): ITabView.TabBadge? {
                return null
            }

            override fun getBackground(position: Int): Int {
                return -1
            }

            override fun getTitle(position: Int): ITabView.TabTitle? {
                return ITabView.TabTitle.Builder()
                    .setContent(data[position].name)
                    .setTextColor(
                        ContextCompat.getColor(mActivity, R.color.colorAccent),
                        ContextCompat.getColor(mActivity, R.color.Grey500))
                    .build()
            }

            override fun getCount(): Int {
                return data.size
            }

        })


    }

    /**
     * 右侧数据
     * @param data MutableList<NavigationBean>
     */
    fun setRight(data: MutableList<NavigationBean>){
        mViewBinding.recyclerView.run {
            adapter = NavigationAdapter(data)
            layoutManager = linearLayoutManager
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }
        mViewBinding.recyclerView.adapter = NavigationAdapter(data)
    }

    override fun init() {

    }

    override fun lazyLoadData() {
        mViewModel.getNavigationList()
    }

    override fun getViewModelClass() = SystemModel::class.java

    override fun getViewBinding() = FragmentNavigationBinding.inflate(layoutInflater)


}
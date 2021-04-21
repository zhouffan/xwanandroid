package org.fw.x_wanandroid.ui.main.system

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
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

    override fun observe() {
//        mViewModel.navigationData.observe(this){
//            setLeft(it)
//        }
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
                return 0
            }

            override fun getTitle(position: Int): ITabView.TabTitle? {
                return null
            }

            override fun getCount(): Int {
                return data.size
            }

        })
    }

    override fun init() {

    }

    override fun lazyLoadData() {
        mViewModel.getNavigationList()
    }

    override fun getViewModelClass() = SystemModel::class.java

    override fun getViewBinding() = FragmentNavigationBinding.inflate(layoutInflater)


}
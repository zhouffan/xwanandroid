package org.fw.x_wanandroid.ui.main.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.fw.base_library.base.BaseVmFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.fw.x_wanandroid.R
import org.fw.x_wanandroid.databinding.FragmentProjectBinding
import org.fw.x_wanandroid.ui.main.wechat.ChildFragmentAdapter
import org.fw.x_wanandroid.ui.main.wechat.PersonalArticleFragment

/**
 * A simple [Fragment] subclass.
 * Use the [ProjectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProjectFragment : BaseVmFragment<FragmentProjectBinding, ProjectModel>() {
    companion object {
        @JvmStatic
        fun newInstance() = ProjectFragment()
    }

    override fun observe() {
        mViewModel.data.observe(this){ child ->
            run {
                val titles: MutableList<String> = mutableListOf()
                val fragments: MutableList<Fragment> = mutableListOf()
                child.forEach {
                    titles.add(it.name)
                    fragments.add(PersonalArticleFragment.newInstance(it.id))
                }
                mViewBinding.viewpager2.adapter = ChildFragmentAdapter(fragments, fragment)
                //缓存页面
                mViewBinding.viewpager2.offscreenPageLimit = fragments.size
                //联动工具类：TabLayoutMediator
                TabLayoutMediator(mViewBinding.tableLayout, mViewBinding.viewpager2, true,
                    TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                        tab.text = titles[position]
                    }
                ).attach()
            }
        }
    }

    override fun init() {

    }

    override fun lazyLoadData() {
        mViewModel.getProjectTree()
    }

    override fun getViewModelClass() = ProjectModel::class.java

    override fun getViewBinding() = FragmentProjectBinding.inflate(layoutInflater)
}
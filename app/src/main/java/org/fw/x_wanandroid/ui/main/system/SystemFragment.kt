package org.fw.x_wanandroid.ui.main.system

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fw.base_library.base.BaseVmFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.fw.x_wanandroid.R
import org.fw.x_wanandroid.databinding.FragmentTreeBinding
import org.fw.x_wanandroid.ui.main.wechat.PersonalArticleFragment
import org.fw.x_wanandroid.ui.main.wechat.WechatFragment

/**
 * A simple [Fragment] subclass.
 * Use the [SystemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SystemFragment : BaseVmFragment<FragmentTreeBinding, SystemModel>() {
    companion object {
        @JvmStatic
        fun newInstance() = SystemFragment()
    }

    override fun observe() {

    }

    override fun init() {
        val titles: MutableList<String> = mutableListOf()
        val fragments: MutableList<Fragment> = mutableListOf()
        titles.add("体系")
        titles.add("导航")
        fragments.add(KnowledgeFragment.newInstance())
        fragments.add(NavigationFragment.newInstance())
        mViewBinding.viewpager2.adapter = WechatFragment.ChildFragmentAdapter(fragments, fragment)
        //缓存页面
        mViewBinding.viewpager2.offscreenPageLimit = fragments.size
        //联动工具类：TabLayoutMediator
        TabLayoutMediator(mViewBinding.tableLayout, mViewBinding.viewpager2, true,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = titles[position]
            }
        ).attach()
    }

    override fun lazyLoadData() {

    }

    override fun getViewModelClass() = SystemModel::class.java

    override fun getViewBinding() = FragmentTreeBinding.inflate(layoutInflater)


}
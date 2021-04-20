package org.fw.x_wanandroid.ui.main.wechat

import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fw.base_library.base.BaseVmFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.fw.x_wanandroid.databinding.FragmentPublicBinding


class WechatFragment : BaseVmFragment<FragmentPublicBinding, WechatModel>() {
    companion object {
        @JvmStatic
        fun newInstance() = WechatFragment()
    }

    override fun observe() {
        mViewModel.wxArticleData.observe(this){
            child ->
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
        mViewModel.getWXArticle()
    }

    override fun getViewModelClass() = WechatModel::class.java

    override fun getViewBinding() = FragmentPublicBinding.inflate(layoutInflater)

    class ChildFragmentAdapter(private val pages: MutableList<Fragment>,
                               fragment: Fragment): FragmentStateAdapter(fragment){
        override fun getItemCount() = pages.size

        override fun createFragment(position: Int) =  pages[position]

    }
}
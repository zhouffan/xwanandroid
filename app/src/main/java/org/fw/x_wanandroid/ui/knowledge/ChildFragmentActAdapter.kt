package org.fw.x_wanandroid.ui.knowledge

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/21 22:48
 *    desc   :
 *    version: 1.0
 */
class ChildFragmentActAdapter(private val pages: MutableList<Fragment>,
                              fragmentActivity: FragmentActivity
): FragmentStateAdapter(fragmentActivity){

    override fun getItemCount() = pages.size

    override fun createFragment(position: Int) =  pages[position]

}


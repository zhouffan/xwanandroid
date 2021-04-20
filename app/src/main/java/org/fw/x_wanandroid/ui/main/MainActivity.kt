package org.fw.x_wanandroid.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.fw.base_library.base.BaseActivity
import org.fw.x_wanandroid.R
import org.fw.x_wanandroid.databinding.ActivityMainBinding
import org.fw.x_wanandroid.ui.main.home.HomeFragment
import org.fw.x_wanandroid.ui.main.plaza.PlazaFragment
import org.fw.x_wanandroid.ui.main.project.ProjectFragment
import org.fw.x_wanandroid.ui.main.wechat.WechatFragment
import org.fw.x_wanandroid.ui.main.tree.TreeFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initialize() {
        super.initialize()

        val bottomNav = mViewBinding.bottomNav
        //viewpager2
        val viewPager = mViewBinding.vp2
        val fragments = mutableListOf(HomeFragment.newInstance(),
        PlazaFragment.newInstance(), WechatFragment.newInstance(), TreeFragment.newInstance(),
        ProjectFragment.newInstance())
        //********
        //********
        //********
        viewPager.offscreenPageLimit = 1
        viewPager.adapter = MyPagerAdapter(fragments, this)

        viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNav.menu.getItem(position).isChecked = true
            }
        })
        //BottomNavigationView 监听
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> viewPager.currentItem = 0
                R.id.navigation_plaza -> viewPager.currentItem = 1
                R.id.navigation_public -> viewPager.currentItem = 2
                R.id.navigation_tree -> viewPager.currentItem = 3
                R.id.navigation_project -> viewPager.currentItem = 4
            }
            false
        }

        viewPager.setCurrentItem(2,false)
    }

    class MyPagerAdapter(private val fragmentList: MutableList<Fragment>, fragmentActivity: FragmentActivity)
        : FragmentStateAdapter(fragmentActivity){

        override fun getItemCount() = fragmentList.size

        override fun createFragment(position: Int): Fragment = fragmentList[position]


    }
}
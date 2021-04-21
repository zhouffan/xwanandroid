package org.fw.x_wanandroid.ui.knowledge

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.fw.base_library.base.BaseActivity
import com.fw.base_library.base.BaseVmActivity
import com.google.android.material.tabs.TabLayoutMediator
import org.fw.x_wanandroid.bean.KnowledgeTree
import org.fw.x_wanandroid.bean.Wechat
import org.fw.x_wanandroid.config.Constant.CONTENT_DATA_KEY
import org.fw.x_wanandroid.config.Constant.CONTENT_TITLE_KEY
import org.fw.x_wanandroid.databinding.ActivityKnowledgeBinding
import org.fw.x_wanandroid.ui.detail.DetailActivity
import org.fw.x_wanandroid.ui.main.wechat.ChildFragmentAdapter
import org.fw.x_wanandroid.ui.main.wechat.PersonalArticleFragment



class KnowledgeActivity : BaseActivity<ActivityKnowledgeBinding>() {
    companion object{
        fun startMe(context: Context, title: String, data: KnowledgeTree){
            val intent = Intent(context, KnowledgeActivity::class.java)
            intent.putExtra(CONTENT_TITLE_KEY, title)
            intent.putExtra(CONTENT_DATA_KEY, data)
            context.startActivity(intent)
        }
    }
    private var knowledges = mutableListOf<Wechat>()
    private var title: String= ""

    override fun getViewBinding() = ActivityKnowledgeBinding.inflate(layoutInflater)

    override fun initialize() {
        super.initialize()
        intent.extras?.let {
            title = it.getString(CONTENT_TITLE_KEY).toString()
            val data = it.getSerializable(CONTENT_DATA_KEY) as KnowledgeTree
            knowledges.addAll(data.children)

            val titles: MutableList<String> = mutableListOf()
            val fragments: MutableList<Fragment> = mutableListOf()
            knowledges.forEach {
                child->
                    titles.add(child.name)
                    fragments.add(PersonalArticleFragment.newInstance(child.id))
            }
            mViewBinding.viewpager2.adapter = ChildFragmentActAdapter(fragments, mActivity)
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
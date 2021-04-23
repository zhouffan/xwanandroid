package org.fw.x_wanandroid.ui.main.system

import android.app.ActivityOptions
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fw.base_library.util.CommonUtil
import com.nex3z.flowlayout.FlowLayout
import org.fw.x_wanandroid.R
import org.fw.x_wanandroid.bean.Article
import org.fw.x_wanandroid.bean.Data
import org.fw.x_wanandroid.bean.NavigationBean

/**
 * Created by chenxz on 2018/5/13.
 */
class NavigationAdapter(datas: MutableList<NavigationBean>)
    : BaseQuickAdapter<NavigationBean, BaseViewHolder>(R.layout.item_navigation_list, datas) {

    override fun convert(helper: BaseViewHolder, item: NavigationBean) {
        helper.setText(R.id.item_navigation_tv, item.name)
        val flowLayout: FlowLayout = helper.getView(R.id.item_navigation_flow_layout)
        val articles: List<Data> = item.articles
        articles.forEach {
            val textView = LayoutInflater.from(context).inflate(R.layout.flow_layout_tv, null) as TextView
            textView.text = it.title
            textView.setTextColor(CommonUtil.randomColor())
            flowLayout.addView(textView)

            textView.setOnClickListener {

            }
        }
//        flowLayout.run {
//            adapter = object : TagAdapter<Article>(articles) {
//                override fun getView(parent: FlowLayout?, position: Int, article: Article?): View? {
//
//                    val tv: TextView = LayoutInflater.from(parent?.context).inflate(R.layout.flow_layout_tv,
//                            flowLayout, false) as TextView
//
//                    article ?: return null
//
//                    val padding: Int = DisplayManager.dip2px(10F)
//                    tv.setPadding(padding, padding, padding, padding)
//                    tv.text = article.title
//                    tv.setTextColor(CommonUtil.randomColor())
//
//                    setOnTagClickListener { view, position, _ ->
//                        val options: ActivityOptions = ActivityOptions.makeScaleUpAnimation(view,
//                                view.width / 2,
//                                view.height / 2,
//                                0,
//                                0)
//                        val data: Article = articles[position]
//                        ContentActivity.start(context, data.id, data.title, data.link, options.toBundle())
//                        true
//                    }
//                    return tv
//                }
//            }
//        }
    }


}
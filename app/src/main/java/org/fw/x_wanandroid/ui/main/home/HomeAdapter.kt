package org.fw.x_wanandroid.ui.main.home

import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.fw.base_library.glide.GlideUtil
import org.fw.x_wanandroid.R
import org.fw.x_wanandroid.bean.Data

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/17 21:48
 *    desc   :
 *    version: 1.0
 */
class HomeAdapter(data: MutableList<Data>) :
    BaseQuickAdapter<Data, BaseViewHolder>(R.layout.home_item, data) {

    override fun convert(helper: BaseViewHolder, item: Data) {
        val authorStr = if (item.author.isNotEmpty()) item.author else item.shareUser
        helper.setText(R.id.tv_article_title, Html.fromHtml(item.title))
            .setText(R.id.tv_article_author, authorStr)
            .setText(R.id.tv_article_date, item.niceDate)
            .setImageResource(R.id.iv_like,
                if (item.collect) R.drawable.ic_like else R.drawable.ic_like_not
            )
//            .addOnClickListener(R.id.iv_like)
        val chapterName = when {
            item.superChapterName.isNotEmpty() and item.chapterName.isNotEmpty() ->
                "${item.superChapterName} / ${item.chapterName}"
            item.superChapterName.isNotEmpty() -> item.superChapterName
            item.chapterName.isNotEmpty() -> item.chapterName
            else -> ""
        }
        helper.setText(R.id.tv_article_chapterName, chapterName)
        if (item.envelopePic.isNotEmpty()) {
            helper.getView<ImageView>(R.id.iv_article_thumbnail)
                .visibility = View.VISIBLE
            context?.let {
                GlideUtil.show(context, item.envelopePic, helper.getView(R.id.iv_article_thumbnail))
            }
        } else {
            helper.getView<ImageView>(R.id.iv_article_thumbnail)
                .visibility = View.GONE
        }
        val tvFresh = helper.getView<TextView>(R.id.tv_article_fresh)
        if (item.fresh) {
            tvFresh.visibility = View.VISIBLE
        } else {
            tvFresh.visibility = View.GONE
        }
        val tvTop = helper.getView<TextView>(R.id.tv_article_top)
        if (item.top) {
            tvTop.visibility = View.VISIBLE
        } else {
            tvTop.visibility = View.GONE
        }
        val tvArticleTag = helper.getView<TextView>(R.id.tv_article_tag)
        if (item.tags.isNotEmpty()) {
            tvArticleTag.visibility = View.VISIBLE
            tvArticleTag.text = item.tags[0].name
        } else {
            tvArticleTag.visibility = View.GONE
        }
    }

}
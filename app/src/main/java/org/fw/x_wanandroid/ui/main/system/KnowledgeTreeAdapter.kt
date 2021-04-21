package org.fw.x_wanandroid.ui.main.system

import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import org.fw.x_wanandroid.R
import org.fw.x_wanandroid.bean.KnowledgeTree

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/21 21:42
 *    desc   :
 *    version: 1.0
 */
class KnowledgeTreeAdapter(datas: MutableList<KnowledgeTree>):
    BaseQuickAdapter<KnowledgeTree, BaseViewHolder>(R.layout.item_knowledge_tree_list, datas) {
    override fun convert(holder: BaseViewHolder, item: KnowledgeTree) {
        holder.setText(R.id.title_first, item.name)
        val content = item.children.joinToString("   ||   ",
            transform = {children -> Html.fromHtml(children.name) })
        holder.setText(R.id.title_second, content)

    }

}
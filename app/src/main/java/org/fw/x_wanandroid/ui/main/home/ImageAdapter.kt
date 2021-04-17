package org.fw.x_wanandroid.ui.main.home

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.fw.base_library.glide.GlideUtil
import com.fw.base_library.util.LogUtil
import com.fw.base_library.util.px2dp
import com.fw.base_library.util.px2sp
import com.youth.banner.adapter.BannerAdapter
import org.fw.x_wanandroid.R
import org.fw.x_wanandroid.bean.Banner

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/4/17 21:46
 *    desc   : 首页banner
 *    version: 1.0
 */
class ImageAdapter(val fragment: Fragment, data: MutableList<Banner>):
    BannerAdapter<Banner, ImageAdapter.BannerViewHolder>(data){
    class BannerViewHolder(val img: ImageView, val txt: TextView, view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val home = RelativeLayout(parent.context)
        home.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        val img = ImageView(home.context)
        img.layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        home.addView(img)
        val txt = TextView(home.context)
        txt.textSize = px2sp(45f)
        txt.setSingleLine()
        txt.ellipsize = TextUtils.TruncateAt.END
        txt.setTextColor(ContextCompat.getColor(home.context, R.color.white))
        txt.setBackgroundColor(ContextCompat.getColor(home.context, R.color.DCDCDC))
        txt.setPadding(
            px2dp(60), px2dp(60),
            px2dp(800), px2dp(60)
        )
        val layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//            layoutParams.setMargins(50,0,0,0)
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        txt.layoutParams = layoutParams
        home.addView(txt)
        return BannerViewHolder(img, txt, home)
    }

    override fun onBindView(
        holder: BannerViewHolder,
        data: Banner,
        position: Int,
        size: Int
    ) {
        LogUtil.i(data.imagePath)
        //图片加载自己实现
        GlideUtil.show(fragment, data.imagePath, holder.img)
        holder.txt.text = data.title
    }
}
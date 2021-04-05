package com.fw.base_library.util

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fw.base_library.base.BaseApp

/**
 *    author : 进击的巨人
 *    e-mail : zhouffan@qq.com
 *    date   : 2021/3/31 23:05
 *    desc   :
 *    version: 1.0
 */
abstract class BaseRecyclerViewAdapter<T, VH: RecyclerView.ViewHolder>(): RecyclerView.Adapter<VH>(){
    var dataList: MutableList<T> = mutableListOf()
    private var listener: OnItemClickListener? = null

    fun setData(dataList: MutableList<T>){
        this.dataList = dataList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            listener?.onItemClick(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}
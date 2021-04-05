package org.fw.x_wanandroid.test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.fw.base_library.aop.AopOnclick
import com.fw.base_library.aop.SingleClick
import com.fw.base_library.util.BaseRecyclerViewAdapter
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.BezierRadarHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.SpinnerStyle
import org.fw.x_wanandroid.R

class TestActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var btn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)

        btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener(this)

        val datas = mutableListOf<String>()
        for(i in 1 .. 50){
            datas.add("textview:$i")
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = TestAdapter()
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = GridLayoutManager(this, 2)
//        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
        adapter.setData(datas)
        adapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@TestActivity, "$position", Toast.LENGTH_SHORT).show()
            }

        })


        //上下拉刷新
        val refreshLayout: RefreshLayout = findViewById<SmartRefreshLayout>(R.id.refreshLayout)
        refreshLayout.setRefreshHeader(BezierRadarHeader(application).setEnableHorizontalDrag(true))
        refreshLayout.setRefreshFooter(BallPulseFooter(application).setSpinnerStyle(SpinnerStyle.Translate))
        refreshLayout.setOnRefreshListener {
            recyclerView.postDelayed({
                //请求数据，填写数据
                refreshLayout.finishRefresh()
            }, 10*1000)
        }
        refreshLayout.autoRefresh()
    }

    var saopSum = 0
//    @AopOnclick
    @SingleClick(value = 10*1000)
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn -> {
                btn.text = "aop自定义时间点击次数:${saopSum++}次"
                Log.i("activity","==>click")
                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }
    }
}



class TestAdapter: BaseRecyclerViewAdapter<String, TestAdapter.MyViewHolder>(){
    class MyViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        val leftView = itemView.findViewById<TextView>(R.id.left)
        val rightView = itemView.findViewById<TextView>(R.id.right)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        /**
         * inflater.inflate(R.layout.linearlayout, parentView ,true);     将第一个参数 add 到第二个参数view上
         * inflater.inflate(R.layout.linearlayout, parentView ,false);    将第一个参数 不add 到第二个参数view上， 但第一个参数布局的根view，layout_width和layout_height 有效.
         * inflater.inflate(R.layout.linearlayout, null);    不需要将第一个参数所指定的布局添加到任何容器中，同时也表示没有任何容器来来协助第一个参数所指定布局的根节点生成布局参数.
         *
         * 为什么Activity布局的根节点的宽高属性会生效?
         * ==>我们的页面中有一个顶级View叫做DecorView，DecorView中包含一个竖直方向的LinearLayout，LinearLayout由两部分组成，
         * 第一部分是标题栏，第二部分是内容栏，内容栏是一个FrameLayout，我们在Activity中调用setContentView就是将View添加到这个FrameLayout中
         *
         */
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_rv_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.leftView.text = dataList[position]
        holder.rightView.text = dataList[position]
    }
}


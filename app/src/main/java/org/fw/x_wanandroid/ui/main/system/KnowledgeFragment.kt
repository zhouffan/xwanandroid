package org.fw.x_wanandroid.ui.main.system

import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.fw.base_library.base.BaseVmFragment
import com.fw.base_library.util.SpaceItemDecoration
import org.fw.x_wanandroid.databinding.FragmentKnowledgeBinding
import org.fw.x_wanandroid.ui.knowledge.KnowledgeActivity

/**
 * A simple [Fragment] subclass.
 * Use the [KnowledgeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KnowledgeFragment : BaseVmFragment<FragmentKnowledgeBinding, SystemModel>() {
    lateinit var adapter:KnowledgeTreeAdapter
    companion object {
        @JvmStatic
        fun newInstance() = KnowledgeFragment()
    }

    override fun observe() {
        mViewModel.knowledgeTreeData.observe(this){
            adapter.setNewInstance(it)
        }
    }

    override fun init() {
        mViewBinding.recyclerView.addItemDecoration(SpaceItemDecoration(fragment.requireContext()))
        adapter = KnowledgeTreeAdapter(mutableListOf())
        mViewBinding.recyclerView.adapter = adapter
        adapter.setOnItemClickListener { _, _, position ->
            if (adapter.data.size > 0){
                val data = adapter.data[position]
                KnowledgeActivity.startMe(mActivity, data.name, data)
            }
        }
    }

    override fun lazyLoadData() {
        mViewModel.getKnowledgeTree()
    }

    override fun getViewModelClass() = SystemModel::class.java

    override fun getViewBinding(): FragmentKnowledgeBinding  = FragmentKnowledgeBinding.inflate(layoutInflater)

}
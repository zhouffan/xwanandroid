package org.fw.x_wanandroid.ui.main.plaza

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.fw.x_wanandroid.R


/**
 * A simple [Fragment] subclass.
 * Use the [PlazaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlazaFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plaza, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlazaFragment()
    }
}
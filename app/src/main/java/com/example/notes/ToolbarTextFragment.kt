package com.example.notes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_toolbar_text.*

class ToolbarTextFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toolbar_text, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fe1bconfirm.setOnClickListener { onClick() }
    }

    fun onClick() {
        var frag = fragmentManager!!.findFragmentById(R.id.recyclerFragment) as RecyclerFragment
        frag.editNote()
    }

}

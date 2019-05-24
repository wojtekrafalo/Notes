package com.example.notes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_recycler.*
import java.util.ArrayList

class RecyclerFragment : Fragment() {

    var subnotes = ArrayList<Subnote>()
    var myadapter: MyAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recycler, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        retainInstance = true

        prepareRecycler()
    }

    private fun prepareRecycler() {
        rv.layoutManager = LinearLayoutManager(context!!)
        myadapter = MyAdapter(subnotes, context!!, { subnote: Subnote -> subnoteClicked(subnote) })
        rv.adapter = myadapter
    }

    fun changeNote(newSubnotes: ArrayList<Subnote>) {
        this.subnotes = newSubnotes
        prepareRecycler()
        setToolbar(-1)
    }

    fun addSubnote(type: Int) {
        when(type) {
            0 -> {
                subnotes.add(SubnoteText(subnotes.size, "Tekst"))
            }
            1 -> {
                subnotes.add(SubnoteImage(subnotes.size, "Obrazek"))
            }
            2 -> {
                subnotes.add(SubnoteDrawing(subnotes.size, "Rysunek"))
            }
        }
        myadapter?.notifyDataSetChanged()
        myadapter?.activeID = subnotes.size - 1
    }

    fun setToolbar(type : Int) {
        var frag: Fragment
        val transaction = fragmentManager!!.beginTransaction()
        when(type) {
            0 -> frag = ToolbarTextFragment()
            1 -> frag = ToolbarImageFragment()
            2 -> frag = ToolbarDrawingFragment()
            else -> frag = ToolbarEmptyFragment()
        }
        transaction.replace(R.id.container, frag)
        transaction.commit()
    }

    private fun subnoteClicked(subnote : Subnote) {
        if(myadapter?.activeID != subnote.id) {
            setToolbar(subnote.type)
            myadapter?.activeID = subnote.id
        } else {
            setToolbar(-1)
            myadapter?.activeID = -1
        }
    }

    fun editNote() {
        myadapter?.editNote()
        myadapter?.notifyDataSetChanged()
    }
}

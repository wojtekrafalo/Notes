package com.example.notes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_recycler.*
import java.util.ArrayList

class RecyclerFragment : Fragment() {

    var subnotes = ArrayList<Fragment>()
    var myadapter: MyAdapter? = null
    private lateinit var rv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rv = inflater.inflate(R.layout.fragment_recycler, container, false) as RecyclerView
        return rv
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        retainInstance = true

        prepareRecycler()
    }

    private fun prepareRecycler() {
        rv.layoutManager = LinearLayoutManager(context!!)
        myadapter = MyAdapter(subnotes, context!!)
        rv.adapter = myadapter
    }

    fun changeNote(newSubnotes: ArrayList<Fragment>) {
        this.subnotes = newSubnotes
        prepareRecycler()
        setToolbar(-1)
    }

    fun addSubnote(type: Int) {
        //subnotes.add(PaintFragment())
        when(type) {
            0 -> {
                subnotes.add(PaintFragment())
            }
            1 -> {
                subnotes.add(EditTextFragment())
            }

        }
        myadapter?.notifyDataSetChanged()
        myadapter?.activeID = subnotes.size - 1
    }

    fun setToolbar(type : Int) {
        val frag: Fragment
        val transaction = fragmentManager!!.beginTransaction()
        frag = PaintMenuFragment()
        /*when(type) {
            0 -> frag = PaintMenuFragment()
            //else -> frag = ToolbarImageFragment()
        }*/
        transaction.replace(R.id.container, frag)
        transaction.commit()
    }



    fun editNote() {
//        myadapter?.editNote()
        myadapter?.notifyDataSetChanged()
    }
}

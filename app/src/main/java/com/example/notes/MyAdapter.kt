package com.example.notes

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class MyAdapter(var subnotes: ArrayList<Fragment>, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var activeID: Int = 0

    override fun getItemViewType(position: Int): Int {
        return if(subnotes[position] is PaintFragment)
            0
        else
            1
    }

    override fun getItemCount(): Int {
        return subnotes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> ViewHolderr(LayoutInflater.from(context).inflate(R.layout.fragment_paint, parent, false))
            else -> ViewHolderr(LayoutInflater.from(context).inflate(R.layout.fragment_edit_text, parent, false))
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        /*
        Ważneeee
         */

        if(subnotes[position] is PaintFragment){
            holder.itemView.setOnClickListener {
                //TODO
                val menu = PaintMenuFragment()
                menu.setPaintFragment(subnotes[position] as PaintFragment)
            }
        }else if (subnotes[position] is EditTextFragment){
            holder.itemView.setOnClickListener {
                //TODO
                val menu = EditTextMenuFragment()
                menu.setEditTextFragment(subnotes[position] as EditTextFragment)
            }
        }


        /*when(holder.itemViewType) {
            0 -> {
                var subnoteTextFragment: SubnoteTextFragment = subnotes[position] as SubnoteTextFragment
//                holder.itemView.f1textView.text = "teeeest"//subnoteTextFragment.text
            }
            1 -> {
                var subnoteImageFragment: SubnoteImageFragment = subnotes[position] as SubnoteImageFragment
//                holder.itemView.f2textView.text = subnoteImage.img
            }
            2 -> {
                var subnoteDrawingFragment: SubnoteDrawingFragment = subnotes[position] as SubnoteDrawingFragment
//                holder.itemView.f3textView.text = subnoteDrawing.drawing
            }
        }
        holder.itemView.setOnClickListener{
            clickListener(subnotes[position])
        }*/
    }

    fun editNote() {
        /*var subnote: SubnoteFragment
        when(subnotes[activeID].type) {
            0 -> {
                subnote = subnotes[activeID] as SubnoteTextFragment
//                subnote.setText("EDIT")
                // TODO
                subnotes[activeID] = subnote
            }
            1 -> {
                subnote = subnotes[activeID] as SubnoteImageFragment
                // TODO
                subnotes[activeID] = subnote
            }
            2 -> {
                subnote = subnotes[activeID] as SubnoteDrawingFragment
                // TODO
                subnotes[activeID] = subnote
            }
        }*/
    }
}

class ViewHolderr (view: View) : RecyclerView.ViewHolder(view) {

}
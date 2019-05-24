package com.example.notes

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_subnote_text.view.*
import kotlinx.android.synthetic.main.fragment_subnote_drawing.view.*

class MyAdapter(var subnotes: ArrayList<SubnoteFragment>, val context: Context,
                val clickListener: (SubnoteFragment) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var activeID: Int = 0

    override fun getItemViewType(position: Int): Int {
        return subnotes[position].type
    }

    override fun getItemCount(): Int {
        return subnotes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            0 -> return ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_subnote_text, parent, false))
            1 -> return ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_subnote_image, parent, false))
            2 -> return ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_subnote_drawing, parent, false))
            else -> return ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_subnote_text, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
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
        }
    }

    fun editNote() {
        var subnote: SubnoteFragment
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
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

}
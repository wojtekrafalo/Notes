package com.example.notes
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.note_category.view.*

class MainAdapter(private var items : ArrayList<NoteCategory>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {


    // Gets the number of categories in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.note_category, parent, false))
    }

    // Binds each note category in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNoteTitle.text = items[position].title
        holder.tvNoteDescription.text = items[position].description

    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each note category to
    val tvNoteTitle = view.title!!
    val tvNoteDescription = view.description!!

}

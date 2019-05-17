package com.example.notes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_category_dialog.view.*
import kotlinx.android.synthetic.main.note_category.view.*

class MainActivity : AppCompatActivity() {

    // Initializing an empty ArrayList to be filled with categories
    private val notes: ArrayList<NoteCategory> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Loads categories into the ArrayList
        addCategories()

        // Creates a vertical Layout Manager
        rv_notes.layoutManager = LinearLayoutManager(this)

        // Access the RecyclerView Adapter and load the data into it
        rv_notes.adapter = MainAdapter(notes, this)

        addButton.setOnClickListener{
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_category_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Add Category")
            val mAlertDialog = mBuilder.show()
            mDialogView.addCategory.setOnClickListener {
                mAlertDialog.dismiss()
                val title = mDialogView.ettitle.text.toString()
                val description = mDialogView.etdescription.text.toString()
                notes.add(NoteCategory(title, description))
            }
        }
    }

    private fun addCategories(){
        notes.add(NoteCategory("dog", "omg"))
        notes.add(NoteCategory("cat", "umm"))
        notes.add(NoteCategory("owl", "hoo"))
    }



}
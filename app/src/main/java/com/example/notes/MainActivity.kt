package com.example.notes

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_category_dialog.view.*

class MainActivity : AppCompatActivity() {

    // Initializing an empty ArrayList to be filled with categories
    private val notes: ArrayList<NoteCategory> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creates a vertical Layout Manager
        rv_notes.layoutManager = LinearLayoutManager(this)

        // Access the RecyclerView Adapter and load the data into it
        rv_notes.adapter = MainAdapter(notes, this, {noteCategory : NoteCategory -> categoryClicked(noteCategory)})

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

    private fun categoryClicked(noteCategory : NoteCategory) {

        Toast.makeText(this, "Clicked: ${noteCategory.title}", Toast.LENGTH_LONG).show()

        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            var myintent = Intent(this, NoteActivity::class.java)
            startActivity(myintent)
        }
        else {

        }
    }


}
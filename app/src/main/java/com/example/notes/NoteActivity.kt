package com.example.notes

import android.arch.persistence.room.Room
import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_note.*
import java.lang.Exception

class NoteActivity : AppCompatActivity() {

    private lateinit var database : NotesDatabase

    var idInDB: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        idInDB = intent.getLongExtra("idInDB", 0)

        titleView.text = intent.getStringExtra("title")
        descriptionView.text = intent.getStringExtra("description")

        noteInit()
    }

    private fun noteInit() {

        AsyncTask.execute {

            try {
                database = Room.databaseBuilder(
                    this,
                    NotesDatabase::class.java,
                    "notes.db"
                ).fallbackToDestructiveMigration().build()
            } catch (e: Exception) {
                Log.i("am2019", e.message)
            }

            if(database.notesDao().getNote(idInDB!!) == null) {
                database.notesDao().insertAll(Note("", Color.RED, 2f, 0f, "", idInDB!!))
            }

        }
    }

    fun onClick(view: View) {
        when(view.getId()) {
            R.id.buttonText -> {
                var intent = Intent(this, NoteTextActivity::class.java)
                intent.putExtra("idInDB", idInDB)
                startActivity(intent)
            }
            R.id.buttonPaint -> {
                var intent = Intent(this, NotePaintActivity::class.java)
                intent.putExtra("idInDB", idInDB)
                startActivity(intent)
            }
        }
    }
}

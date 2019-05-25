package com.example.notes

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_note_paint.*
import kotlinx.android.synthetic.main.fragment_edit_text.*
import java.lang.Exception

class NotePaintActivity : AppCompatActivity() {

    private lateinit var database : NotesDatabase

    private lateinit var noteFragment : Fragment
    private lateinit var menuFragment : Fragment

    var idInDB: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_paint)

        AsyncTask.execute {
            try {
                database = Room.databaseBuilder(
                    this,
                    NotesDatabase::class.java,
                    "notes.db"
                ).build()
            } catch (e: Exception) {
                Log.i("am2019", e.message)
            }
            println("WITAMWITAM: " + getFromDB())
        }

        idInDB = intent.getIntExtra("idInDB", 0)

        noteFragment = noteFrag
        menuFragment = menuFrag
        (menuFragment as PaintMenuFragment).setPaintFragment(noteFragment as PaintFragment)


//        println(getFromDB())
//        noteFragment.loadData(getFromDB(idInDB, "paint"))
    }

    fun onClick(view: View) {
        when(view.getId()) {
            R.id.buttonSave -> {

                AsyncTask.execute {
                    try {
                        database = Room.databaseBuilder(
                            this,
                            NotesDatabase::class.java,
                            "notes.db"
                        ).build()
                    } catch (e: Exception) {
                        Log.i("am2019", e.message)
                    }
                    println("asdfgh: " + getFromDB())
                }

            }
        }
    }

    fun getFromDB() : String {
        var note = database.notesDao().getNote(idInDB!!)
        return note.text
    }
}

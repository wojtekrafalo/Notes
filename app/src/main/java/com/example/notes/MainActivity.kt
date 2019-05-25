package com.example.notes

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var database : NotesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /////////////////

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

            val s1 = Note("testtest123")
            database.notesDao().insertAll(s1)
            val s2 = Note("testttt222")
            database.notesDao().insertAll(s2)

        }


        var intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("idInDB", 1)
        startActivity(intent)
    }
}

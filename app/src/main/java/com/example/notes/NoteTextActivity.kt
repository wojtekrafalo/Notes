package com.example.notes

import android.arch.persistence.room.Room
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_note_text.*
import kotlinx.android.synthetic.main.fragment_edit_text.*
import kotlinx.android.synthetic.main.fragment_paint.*
import java.lang.Exception

class NoteTextActivity : AppCompatActivity(), EditTextFragment.OnFragmentInteractionListener {
    override fun onFragmentInteractionEdit(editText: EditText) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var database : NotesDatabase

    private lateinit var noteFragment : Fragment
    private lateinit var menuFragment : Fragment

    var idInDB: Int? = null

    lateinit var note : Note
    lateinit var newNote : Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_text)

        idInDB = intent.getIntExtra("idInDB", 0)

        noteFragment = this.noteFrag
        menuFragment = this.menuFrag
        (menuFragment as EditTextMenuFragment).setEditTextFragment(noteFragment as EditTextFragment)

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
            note = database.notesDao().getNote(idInDB!!)
            noteFragment.fragEditText.setText(note.text)
        }
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
//                    database.notesDao().updateTextNote(idInDB!!, noteFragment.fragEditText.getText())
                }

            }
        }
    }
}

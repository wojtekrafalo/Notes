package com.example.notes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_note_text.*

class NoteTextActivity : AppCompatActivity(), EditTextFragment.OnFragmentInteractionListener {
    override fun onFragmentInteractionEdit(editText: EditText) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var noteFragment : Fragment
    private lateinit var menuFragment : Fragment

    var idInDB: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_text)

        idInDB = intent.getIntExtra("idInDB", 0)

        noteFragment = this.noteFrag
        menuFragment = this.menuFrag
        (menuFragment as EditTextMenuFragment).setEditTextFragment(noteFragment as EditTextFragment)

//        noteFragment.loadData(getFromDB(idInDB, "text"))
    }

    fun onClick(view: View) {
        when(view.getId()) {
            R.id.buttonSave -> {
//                sendToDB(noteFragment.saveData, "text")
            }
        }
    }
}

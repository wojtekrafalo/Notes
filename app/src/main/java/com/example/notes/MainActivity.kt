package com.example.notes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import java.util.ArrayList

class MainActivity : AppCompatActivity() {


    var notes = ArrayList<ArrayList<Fragment>>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addToolbar()

        addNote()
        addNote()
    }

    fun onClick(view: View) {
        var frag = supportFragmentManager.findFragmentById(R.id.recyclerFragment) as RecyclerFragment
        when(view.getId()) {
            R.id.button -> {
                frag.addSubnote(0)
                frag.setToolbar(0)
            }
            R.id.button2-> {
                frag.addSubnote(1)
                frag.setToolbar(1)
            }
            R.id.button3 -> {
                frag.addSubnote(2)
                frag.setToolbar(2)
            }

        }
    }

    fun addToolbar() {
        val frag = ToolbarEmptyFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, frag)
        transaction.commit()
    }

    fun addNote() {
        notes.add(ArrayList())
    }
}

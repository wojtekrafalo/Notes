package com.example.notes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), EditTextFragment.OnFragmentInteractionListener {
    private var numberOfFragments:Int = 0
    private lateinit var editTextFragment:EditTextFragment
    private lateinit var editTextMenuFragment:EditTextMenuFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextFragment = fragEditTextFragment as EditTextFragment
        editTextMenuFragment = fragEditTextMenuFragment as EditTextMenuFragment
        editTextMenuFragment.setListener(editTextFragment)
    }


    private fun addEditTextFragment (){
        val frag:EditTextFragment = EditTextFragment.newInstance()
        supportFragmentManager.beginTransaction().add(numberOfFragments, frag).commit()
//        frag.setListener(this)
        numberOfFragments++
    }

    override fun onFragmentInteractionEdit(editText:EditText) {
//        this.fragEditText = editText
//        println("CAPTURED_MOTHERFUCKER " + editText.text.toString())
    }
}

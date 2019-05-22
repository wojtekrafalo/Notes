package com.example.notes

import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_edit_text.*
import top.defaults.colorpicker.ColorPickerPopup


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, EditTextFragment.OnFragmentInteractionListener {
    private var mySpannable : SpannableStringBuilder = SpannableStringBuilder()
//    private lateinit var fragEditText: EditText
    private var eText : Int = 0
    private lateinit var edit : android.support.v7.widget.AppCompatEditText
    private var numberOfFragments:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ArrayAdapter.createFromResource(
            this,
            R.array.fonts,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this

//        println(editText::class.java.toString())

//        fragEditText.setOnTouchListener { v, clickEvent ->
//            eText = v.id
//            v.setBackgroundColor(Color.RED)
//            edit = v as AppCompatEditText
//
//            println("KLIKNIETY_DEBILU  " + edit.text.toString())
//            true
//        }

//        addEditTextFragment()
    }

    fun clickU (view: View) {
        mySpannable = SpannableStringBuilder(fragEditText.text.toString())
        mySpannable.setSpan(
            UnderlineSpan(),
            fragEditText.selectionStart, fragEditText.selectionEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        fragEditText.text = mySpannable
    }

    fun clickB (view: View) {
        mySpannable = SpannableStringBuilder(fragEditText.text.toString())
        mySpannable.setSpan(
            android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
            fragEditText.selectionStart, fragEditText.selectionEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        fragEditText.text = mySpannable
    }

    fun clickI (view: View) {
        mySpannable = SpannableStringBuilder(fragEditText.text.toString())
        mySpannable.setSpan(
            android.text.style.StyleSpan(android.graphics.Typeface.ITALIC),
            fragEditText.selectionStart, fragEditText.selectionEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        fragEditText.text = mySpannable
    }

    fun clickC (view: View) {

        class ColorDialog : ColorPickerPopup.ColorPickerObserver() {
            override fun onColorPicked(color: Int) {

                mySpannable = SpannableStringBuilder(fragEditText.text.toString())
                mySpannable.setSpan(
                    ForegroundColorSpan(color),
                    fragEditText.selectionStart, fragEditText.selectionEnd,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                fragEditText.text = mySpannable
            }
            fun onColor(color: Int, fromUser: Boolean) {}
        }

        val myColorDialog = ColorDialog()

        ColorPickerPopup.Builder(this)
            .initialColor(Color.RED)
            .enableBrightness(true)
            .okTitle("CONFIRM")
            .cancelTitle("CANCEL")
            .showIndicator(true)
            .showValue(true)
            .build()
            .show(view, myColorDialog)
    }


    fun clickedEdit (view: View) {
        mySpannable = SpannableStringBuilder(fragEditText.text.toString())
        mySpannable.setSpan(
            android.text.style.StyleSpan(android.graphics.Typeface.ITALIC),
            fragEditText.selectionStart, fragEditText.selectionEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        fragEditText.text = mySpannable
    }


    private fun addEditTextFragment (){
        val frag:EditTextFragment = EditTextFragment.newInstance(numberOfFragments.toString(),"TRASH")
//        frag.onAttach(this)
        supportFragmentManager.beginTransaction().add(numberOfFragments, frag).commit()
        frag.setListener(this)
        numberOfFragments++
    }



    override fun onNothingSelected(parent: AdapterView<*>?) {}
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        fragEditText.typeface = Typeface.create(parent!!.getItemAtPosition(position).toString(), Typeface.NORMAL)
    }

    override fun onFragmentInteractionEdit(editText:EditText) {
//        this.fragEditText = editText
        println("CAPTURED_MOTHERFUCKER " + editText.text.toString())
    }
}

package com.example.notes

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.text.style.TypefaceSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.notes.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.my_fragment.*
import top.defaults.colorpicker.ColorPickerPopup
import java.lang.NullPointerException
import java.util.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var mySpannable : SpannableStringBuilder = SpannableStringBuilder()

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
    }

    fun clickU (view: View) {
//        Toast.makeText(this, editText.text[editText.selectionStart].toString(), Toast.LENGTH_SHORT).show()
        mySpannable = SpannableStringBuilder(editText.text.toString())
        mySpannable.setSpan(
            UnderlineSpan(),
            editText.selectionStart, editText.selectionEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        editText.text = mySpannable

//        var next:Int
//        var i=0
//        val size:Int=spannable.length
//
//        val spanList = spannable.getSpans(0, size, Object::class.java)
//
//        while(i < size-1) {
////            next = spannable.nextSpanTransition(i, size, CharacterStyle::class.java)
//            spannable.setSpan(spanList[i], spannable.getSpanStart(spanList[i]),spannable.getSpanEnd(spanList[i]), spannable.getSpanFlags(spanList[i]))
//            i++
//        }
    }

    fun clickB (view: View) {

        mySpannable = SpannableStringBuilder(editText.text.toString())
        mySpannable.setSpan(
            android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
            editText.selectionStart, editText.selectionEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        editText.text = mySpannable
    }

    fun clickI (view: View) {
        mySpannable = SpannableStringBuilder(editText.text.toString())
        mySpannable.setSpan(
            android.text.style.StyleSpan(android.graphics.Typeface.ITALIC),
            editText.selectionStart, editText.selectionEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        editText.text = mySpannable
    }

    fun clickC (view: View) {

        class ColorDialog : ColorPickerPopup.ColorPickerObserver() {
            override fun onColorPicked(color: Int) {

                mySpannable = SpannableStringBuilder(editText.text.toString())
                mySpannable.setSpan(
                    ForegroundColorSpan(color),
                    editText.selectionStart, editText.selectionEnd,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                editText.text = mySpannable

//                editText.text.toString().forEach { c ->
//                    var sbb = SimpleSpanBuilder( c.toString(), ForegroundColorSpan(color) )
//                    sbb += SimpleSpanBuilder.Span()
//                }
//                var sbb = SimpleSpanBuilder(editText.text.toString(), ForegroundColorSpan(color))
//                sbb += SimpleSpanBuilder.Span(
//                )
//                editText.text = sbb.build()

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




    override fun onNothingSelected(parent: AdapterView<*>?) {}
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//            val spannable = SpannableStringBuilder(editText.text.toString())
        editText.typeface = Typeface.create(parent!!.getItemAtPosition(position).toString(), Typeface.NORMAL)
//            spannable.setSpan(
//                TypefaceSpan(Typeface.createFromAsset(assets, "fonts/font_family_medium")),
//                editText.selectionStart, editText.selectionEnd,
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//            )
//            editText.text = spannable
    }
}

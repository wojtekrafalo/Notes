package com.example.notes

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_edit_text.*
import top.defaults.colorpicker.ColorPickerPopup


class EditTextFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var parent: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_text, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
            parent = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteractionEdit(editText:EditText)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            EditTextFragment().apply {}
    }


    fun clickU () {
        val mySpannable = SpannableStringBuilder(fragEditText.text)
        mySpannable.setSpan(
            UnderlineSpan(),
            fragEditText.selectionStart, fragEditText.selectionEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        fragEditText.text = mySpannable
    }

    fun clickB () {
        val mySpannable = SpannableStringBuilder(fragEditText.text)
        mySpannable.setSpan(
            android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
            fragEditText.selectionStart, fragEditText.selectionEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        fragEditText.text = mySpannable
    }

    fun clickI () {
        val mySpannable = SpannableStringBuilder(fragEditText.text)
        mySpannable.setSpan(
            android.text.style.StyleSpan(android.graphics.Typeface.ITALIC),
            fragEditText.selectionStart, fragEditText.selectionEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        fragEditText.text = mySpannable
    }

    fun clickC (view: View) {

        class ColorDialog : ColorPickerPopup.ColorPickerObserver() {
            override fun onColorPicked(color: Int) {
                val mySpannable = SpannableStringBuilder(fragEditText.text)
                mySpannable.setSpan(
                    ForegroundColorSpan(color),
                    fragEditText.selectionStart, fragEditText.selectionEnd,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                fragEditText.text = mySpannable
            }
        }

        val myColorDialog = ColorDialog()

        ColorPickerPopup.Builder(context)
            .initialColor(Color.RED)
            .enableBrightness(true)
            .okTitle("CONFIRM")
            .cancelTitle("CANCEL")
            .showIndicator(true)
            .showValue(false)
            .build()
            .show(view, myColorDialog)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        println("CHOSEN_ONE")
//              version 1
        fragEditText.typeface = Typeface.create(parent!!.getItemAtPosition(position).toString(), Typeface.NORMAL)

//              version 2
//        val mySpannable = SpannableStringBuilder(fragEditText.text)
//        mySpannable.setSpan(
//            Typeface.create(parent!!.getItemAtPosition(position).toString(), Typeface.NORMAL),
//            fragEditText.selectionStart, fragEditText.selectionEnd,
//            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        fragEditText.text = mySpannable

//              version 3
//        fragEditText.text.setSpan(
//            Typeface.create(parent!!.getItemAtPosition(position).toString(), Typeface.NORMAL),
//            fragEditText.selectionStart, fragEditText.selectionEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//        )
        println("CHOSEN_TWO")
    }
}

package com.example.notes

import android.content.Context
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_edit_text_menu.*
import kotlinx.android.synthetic.main.fragment_edit_text_menu.view.*


class EditTextMenuFragment : Fragment() {
    private var listener: EditTextFragment? = null
    private lateinit var parent:Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val theView = inflater.inflate(R.layout.fragment_edit_text_menu, container, false)

        val c = SpannableString("U")
        c.setSpan(UnderlineSpan(), 0,c.length, 0)
        theView.underline.text = c
        theView.underline.typeface = Typeface.create("serif", Typeface.NORMAL)

        theView.underline.setOnClickListener{listener?.clickU()}
        theView.bold.setOnClickListener{listener?.clickB()}
        theView.italic.setOnClickListener{listener?.clickI()}
        theView.color.setOnClickListener{listener?.clickC(theView)}

        println("I'M_BEFORE_COBY")
        ArrayAdapter.createFromResource(
            parent,
            R.array.fonts,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            theView.spinner.adapter = adapter
        }

        println("I'M_AFTER_COBY")
        return theView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        listener = context
        parent = context
        println("I'M_BEFORE_BEFORE_COBY")
    }

    fun setListener(e: EditTextFragment) {
        listener = e
        spinner.onItemSelectedListener = listener
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            EditTextMenuFragment().apply { }
    }
}

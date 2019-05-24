package com.example.notes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_subnote_text.*

class SubnoteTextFragment : SubnoteFragment() {

    override val type = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subnote_text, container, false)
    }

    fun setText(text: String) {
        this.f1textView.text = text
    }

}

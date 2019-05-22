package com.example.notes

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_paint_menu.*
import kotlinx.android.synthetic.main.fragment_paint_menu.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [PaintMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PaintMenuFragment : Fragment() {
    private var listener: PaintFragment? = null

    fun setPaintFragment(p: PaintFragment){
        listener = p
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val thisView = inflater.inflate(R.layout.fragment_paint_menu, container, false)
        thisView.clearButton.setOnClickListener { listener?.clearCanvas() }
        thisView.brushSizeButton.setOnClickListener { listener?.setStrokeWidth(100f) }
        thisView.colorButton.setOnClickListener { listener?.setColor(255, 255, 0,0) }
        thisView.undoButton.setOnClickListener { listener?.undo()}

        return thisView
    }



    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    companion object {

        private const val TAG = "PaintMenuFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment PaintMenuFragment.
         */
        @JvmStatic
        fun newInstance() =
            PaintMenuFragment().apply {}
    }
}

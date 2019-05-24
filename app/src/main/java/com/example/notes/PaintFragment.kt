package com.example.notes

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_paint.*


/**
 * A simple [Fragment] subclass.
 * Use the [PaintFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PaintFragment : Fragment() {

    companion object {

        private const val TAG = "PaintFragment"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment PaintFragment.
         */
        @JvmStatic
        fun newInstance() =
            PaintFragment().apply {

            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_paint, container, false)
    }


    fun clearCanvas(){
        paintview.clearCanvas()
    }


    fun setStrokeWidth(w:Float){
        paintview.setStrokeWidth(w)
    }

    fun setColor(color: Int){
        paintview.setColor(color)
    }

    fun switchToEraseMode(){
        paintview.switchToEraseMode()
    }

    fun switchToDrawingMode(){
        paintview.switchToDrawingMode()
    }

    fun undo(){
        paintview.undo()
    }




}

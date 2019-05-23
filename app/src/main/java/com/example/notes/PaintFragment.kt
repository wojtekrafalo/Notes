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
 * Activities that contain this fragment must implement the
 * [PaintFragment.OnPaintFragmentInteractionListener] interface
 * to handle interaction events.
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
    private var listener: OnPaintFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_paint, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPaintFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnPaintFragmentInteractionListener {
        fun onPaintFragmentInteraction()
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

package com.example.notes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    PaintFragment.OnPaintFragmentInteractionListener {

    private lateinit var paintFargment : PaintFragment
    private lateinit var paintMenuFargment : PaintMenuFragment


    override fun onPaintFragmentInteraction() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        paintFargment = paintfrag as PaintFragment
        paintMenuFargment = paintmenufrag as PaintMenuFragment
        paintMenuFargment.setPaintFragment(paintFargment)
    }
}

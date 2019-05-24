package com.example.notes

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import java.io.Serializable

class PaintView(context: Context?, attrs: AttributeSet?) : View(context, attrs){
    /*
    Things to save:

    brushColor:Int
    brushWidth:Float
    lowestY:Int
    Paths : String (JSON)
     */

    companion object {
        private const val TAG = "PaintView"
    }

    private var drawingMode=true



    private var scale = 1f
    private var drawPath: Path = Path()

    private var lowestY = 0f
    private var paths: ArrayList<Pair<Path, Paint>> = ArrayList()
    private val background = Paint(Paint.ANTI_ALIAS_FLAG)
    private val brush = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        background.setARGB(255, 0,0,0)
        background.strokeWidth = 50f
        background.style = Paint.Style.STROKE


        brush.setARGB(255, 0,0,255)
        brush.strokeWidth = 2f
        brush.textSize = 40f
        brush.style = Paint.Style.STROKE

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val height = View.MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(width, width+lowestY.toInt())
        scale = width/720f
        Log.d(TAG, " No dobra  w: $width;h:$height")
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(canvas==null) return
        canvas.save()
        canvas.translate(0f, 0f)
        canvas.scale(scale, scale)
        canvas.drawPaint(background)

        paths.forEach{
            canvas.drawPath(it.first, it.second)
        }

        if(drawingMode)
            canvas.drawPath(drawPath, brush)
        else
            canvas.drawPath(drawPath, background)

        canvas.restore()
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchedX = event.x/scale
        val touchedY = event.y/scale

        val action = event.action

        when(action){
            MotionEvent.ACTION_DOWN -> drawPath.moveTo(touchedX, touchedY)
            MotionEvent.ACTION_MOVE -> drawPath.lineTo(touchedX, touchedY)
            MotionEvent.ACTION_UP -> {
                drawPath.setLastPoint(touchedX, touchedY)
                val c:Paint
                if(drawingMode){
                    c = Paint()
                    c.set(brush)
                }
                else{
                    c = background
                }

                paths.add(Pair(drawPath, c))
                drawPath = Path()
            }
            MotionEvent.ACTION_CANCEL -> drawPath.reset()
            MotionEvent.ACTION_OUTSIDE -> drawPath.reset()
        }

        if(lowestY<touchedY)
        {
            lowestY=touchedY
            requestLayout()
        }

        invalidate()

        return true
    }


    fun clearCanvas() {
        paths.clear()
        lowestY=0f
        requestLayout()
    }

    fun setStrokeWidth(w:Float){
        brush.strokeWidth = w
        invalidate()
    }

    fun setColor(color:Int){
        brush.color = color
        invalidate()
    }


    fun switchToEraseMode(){
        drawingMode=false
    }

    fun switchToDrawingMode(){
        drawingMode=true
    }

    fun undo(){
        if(paths.size>0) {
            paths.removeAt(paths.size - 1)
            invalidate()
        }
    }



}
package com.example.notes

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

class PaintView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    companion object {
        private const val TAG = "PaintView"
    }

    private val green = Paint(Paint.ANTI_ALIAS_FLAG)
    private val red = Paint(Paint.ANTI_ALIAS_FLAG)
    private val brush = Paint(Paint.ANTI_ALIAS_FLAG)
    private var scale = 1f
    private var drawPath: Path = Path()

    private var paths: ArrayList<Pair<Path, Paint>> = ArrayList()


    init {
        green.setARGB(255, 0,255,0)
        red.setARGB(255,255,0,0)
        brush.setARGB(255, 0,0,255)
        brush.strokeWidth = 20f
        brush.style = Paint.Style.STROKE

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val height = View.MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(width, width)
        scale = width/720f
        Log.d(TAG, " No dobra  w: $width;h:$height")
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(canvas==null) return
        canvas.save()
        canvas.translate(0f, 0f)
        canvas.scale(scale, scale)
        canvas.drawPaint(green)

        paths.forEach{
            canvas.drawPath(it.first, it.second)
        }
        canvas.drawPath(drawPath, brush)

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
                val c = Paint()
                c.set(brush)
                paths.add(Pair(drawPath, c))
                drawPath = Path()
            }
            MotionEvent.ACTION_CANCEL -> drawPath.reset()
            MotionEvent.ACTION_OUTSIDE -> drawPath.reset()
        }
        invalidate()

        return true
    }

    fun clearCanvas() {
        paths.clear()
        invalidate()
    }

    fun setStrokeWidth(w:Float){
        brush.strokeWidth = w
        invalidate()
    }

    fun setColor(a : Int, r:Int, g:Int, b:Int){
        brush.setARGB(a,r,g,b)
        invalidate()
    }


    fun switchToEraseMode(){

    }

    fun switchToDrawingMode(){

    }

    fun undo(){
        if(paths.size>0)
            paths.removeAt(paths.size-1)
        invalidate()
    }


}
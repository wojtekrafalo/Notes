package com.example.notes

import android.annotation.SuppressLint
import android.content.Context
import android.drm.DrmStore
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.google.gson.*
import java.io.Serializable
import java.lang.reflect.Type
import org.json.JSONArray


class PaintView(context: Context?, attrs: AttributeSet?) : View(context, attrs){
    /*
    Things to save:

    brushColor:Int
    brushWidth:Float
    lowestY:Int
    Paths : Text (zapis w formacie JSON)
     */

    companion object {
        private const val TAG = "PaintView"
    }

    private var drawingMode=true



    private var scale = 1f
    private var drawPath = SerializablePath()

    private var lowestY = 0f
    private var paths: ArrayList<Pair<SerializablePath, Paint>> = ArrayList()
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

    fun getPathsJSON():String{
        //val g = Gson()
        //return g.toJson(paths, ArrayList<Pair<Path, Paint>>().javaClass)
        return ""
    }

    fun setPathsJSON(s:String){
        //val g = GsonBuilder()
        //val g = GsonBuilder()

        paths = ArrayList()
    }

    fun getLowestY():Float{
        return lowestY
    }


    fun setLowestY(y:Float){
        lowestY = y
    }

    fun getBrushWidth():Float{
        return brush.strokeWidth
    }

    fun setBrushWidth(w:Float){
        brush.strokeWidth = w
        invalidate()
    }

    fun setColor(color:Int){
        brush.color = color
        invalidate()
    }

    fun getColor():Int{
        return brush.color
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
                drawPath = SerializablePath()
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


    private class SerializablePath : Path() {
        var movesTypes = ArrayList<Int>()
        var xyArray = ArrayList<Pair<Float,Float>>()

        override fun lineTo(x: Float, y: Float) {
            movesTypes.add(0)
            xyArray.add(Pair(x,y))
            super.lineTo(x, y)
        }

        override fun moveTo(x: Float, y: Float) {
            movesTypes.add(1)
            xyArray.add(Pair(x,y))
            super.moveTo(x, y)
        }

        override fun setLastPoint(dx: Float, dy: Float) {
            movesTypes.add(2)
            xyArray.add(Pair(dx,dy))
            super.setLastPoint(dx, dy)
        }



        private class SerializablePathSerializer : JsonSerializer<SerializablePath> {
            override fun serialize(src: SerializablePath, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {

                val types = JSONArray(src.movesTypes)
                val xy = JSONArray(src.xyArray)
                val o = JsonObject()

                o.add("types", types as JsonElement)
               // o.add("movesTypes", types.)


                return o
            }
        }

        private inner class SerializablePathDeserializer : JsonDeserializer<SerializablePath> {
            @Throws(JsonParseException::class)
            override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): SerializablePath {
                val p = SerializablePath()
                //p.xyArray = json.asJsonObject.getAsJsonArray()

                json.asJsonPrimitive.asString
                return SerializablePath()
            }
        }


    }


}


package nunofernandes.example.customviewteste

import android.content.Context
import android.graphics.*

import android.os.Build
import android.util.AttributeSet

import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi

import java.util.*
import kotlin.collections.ArrayList

class TouchScreenView: View {

    var touch : MutableList<Ball> = ArrayList()
    var touchY = 0F
    var touchX = 0F
    var contador = 0
    var setOnValueChange : ((value : Float) -> Unit)? = null

    constructor(context: Context?) : super(context){
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    fun init(){

    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        val paint = Paint()
        paint.color = Color.RED
        paint.strokeWidth = 3F
        paint.style = Paint.Style.STROKE

        val rect = Rect(1, 1, width-1, height-1)
        canvas?.drawRect(rect, paint)

        var paint2 = Paint()
        paint2.color = Color.BLUE

        for(ball in touch){
            paint2 = ball.paint
            canvas?.drawCircle(ball.x?:0F, ball.y?:0F,20F, paint2)
        }

        //FUNCAO BITMAP
        //roundMapMarkerUserPosition(canvas)
    }

    private fun setRandomColor(paint: Paint) {
        paint.color =
            getRandomColor()
    }

    private fun getRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    //FUNCAO BITMAP
    /*fun roundMapMarkerUserPosition(canvas: Canvas?): Bitmap{
        val bitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888)
        Canvas(bitmap)

        var paint2 = Paint()
        paint2.color = Color.BLUE

        for(ball in touch){
            paint2 = ball.paint
            canvas?.drawCircle(ball.x?:0F, ball.y?:0F,20F, paint2)
        }

        return bitmap
    }*/

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val y = event?.y
        val x = event?.x
        val pinta    = Paint()
        when(event?.action){
            MotionEvent.ACTION_DOWN-> {
                touchY = y?:0F
                touchX = x?:0F
                invalidate()
                setRandomColor(pinta)
                val ball = Ball(touchX, touchY, pinta)
                touch.add(ball)

                setOnValueChange?.let {
                    contador ++
                    it.invoke(contador.toFloat())
                }
            }
        }
        return true
    }
}

class Ball(var x: Float?, var y: Float?, var paint: Paint)

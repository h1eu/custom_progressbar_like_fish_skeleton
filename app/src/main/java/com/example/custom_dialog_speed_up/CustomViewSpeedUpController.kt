package com.example.custom_dialog_speed_up

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

class CustomViewSpeedUpController(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {
    private var viewStart = 0f
    private var viewImg = 0f
    private var isFirst = true
    private var currentPos: Float? = null
    var onUpdateSpeedUp: (Float) -> Unit = {}
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY)
        val height = MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.EXACTLY)
        super.onMeasure(width, height)
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        val drawableProgressPoint = ContextCompat.getDrawable(context, R.drawable.ic_controller)
            ?.let { drawableToBitmap(it) }

        if (drawableProgressPoint != null) {
            if (isFirst) {
                isFirst = false
                viewStart = width.toFloat() / 2
                currentPos = width.toFloat() / 2
            }
            canvas?.drawBitmap(
                drawableProgressPoint,
                viewStart, 0f,
                null
            )
            viewImg = drawableProgressPoint.width.toFloat()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> {
                if (event.x > 0f && event.x < width) {
                    currentPos = event.x
                    if (event.x < width / 2) {
                        onUpdateSpeedUp(calculateSpeedInLeft(event.x))
                    } else {
                        onUpdateSpeedUp(calculateSpeedInRight(event.x))
                    }
                    viewStart = event.x
                    invalidate()

                } else {
                    if (event.x <= 0f) {
                        currentPos = 0f
                        onUpdateSpeedUp(calculateSpeedInLeft(0f))
                        viewStart = 0f
                        invalidate()
                    }
                    if (event.x >= width) {
                        currentPos = width.toFloat()
                        onUpdateSpeedUp(4f)
                        viewStart = width - viewImg
                        invalidate()
                    }

                }
            }

        }
        return true
    }

    private fun calculateSpeedInLeft(value: Float): Float {
        val halfWidth = width / 2
        val percent = value / halfWidth
        return percent * 0.75f + 0.25f

    }

    private fun calculateSpeedInRight(value: Float): Float {
        val halfWidth = width / 2
        val percent = value / halfWidth - 1f
        return percent * 3f + 1f
    }

    fun onPlus(){
        currentPos?.let {
            val halfWidth = width / 2
            if (it >= 0f && it <= width){
                val calculatorLeftPos = String.format("%.2f",calculateSpeedInLeft(it)).toFloat()
                val calculatorRightPos = String.format("%.2f",calculateSpeedInRight(it)).toFloat()
                when(true){
                    (0.25f <= calculatorLeftPos && calculatorLeftPos < 0.50f) -> {
                        val pos1 = (0.50f - 0.25f) / 0.75f * halfWidth
                        currentPos = pos1
                        onUpdateSpeedUp(0.50f)
                        viewStart = pos1
                        invalidate()
                    }
                    (0.50f <= calculatorLeftPos && calculatorLeftPos < 0.75f) -> {
                        val pos1 = (0.75f - 0.25f) / 0.75f * halfWidth
                        currentPos = pos1
                        onUpdateSpeedUp(0.75f)
                        viewStart = pos1
                        invalidate()
                    }
                    (0.75f <= calculatorLeftPos && calculatorLeftPos < 1.00f) -> {
                        val pos1 = (1.00f - 0.25f) / 0.75f * halfWidth
                        currentPos = pos1
                        onUpdateSpeedUp(1.00f)
                        viewStart = pos1
                        invalidate()
                    }
                    (1.00f <= calculatorRightPos && calculatorRightPos < 2.00f) -> {
                        val pos1 = (((2.00f - 1.00f) / 3f) + 1f) * halfWidth
                        currentPos = pos1
                        onUpdateSpeedUp(2.00f)
                        viewStart = pos1
                        invalidate()
                    }
                    (2.00f <= calculatorRightPos && calculatorRightPos < 3.00f) -> {
                        val pos1 = (((3.00f - 1.00f) / 3f) + 1f) * halfWidth
                        currentPos = pos1
                        onUpdateSpeedUp(3.00f)
                        viewStart = pos1
                        invalidate()
                    }
                    (3.00f <= calculatorRightPos && calculatorRightPos < 4.00f) -> {
                        val pos1 = (((4.00f - 1.00f) / 3f) + 1f) * halfWidth
                        currentPos = pos1
                        onUpdateSpeedUp(4.00f)
                        viewStart = pos1 - viewImg
                        invalidate()
                    }
                    else -> {}
                }
            }
        }
    }

    fun onMinus(){
        currentPos?.let {
            val halfWidth = width / 2
            if (it >= 0f && it <= width){
                val calculatorLeftPos = String.format("%.2f",calculateSpeedInLeft(it)).toFloat()
                val calculatorRightPos = String.format("%.2f",calculateSpeedInRight(it)).toFloat()
                when(true){
                    (0.25f < calculatorLeftPos && calculatorLeftPos <= 0.50f) -> {
                        val pos1 = (0.25f - 0.25f) / 0.75f * halfWidth
                        currentPos = pos1
                        onUpdateSpeedUp(0.25f)
                        viewStart = pos1
                        invalidate()
                    }
                    (0.50f < calculatorLeftPos && calculatorLeftPos <= 0.75f) -> {
                        val pos1 = (0.50f - 0.25f) / 0.75f * halfWidth
                        currentPos = pos1
                        onUpdateSpeedUp(0.50f)
                        viewStart = pos1
                        invalidate()
                    }
                    (0.75f < calculatorLeftPos && calculatorLeftPos <= 1.00f) -> {
                        val pos1 = (0.75f - 0.25f) / 0.75f * halfWidth
                        currentPos = pos1
                        onUpdateSpeedUp(0.75f)
                        viewStart = pos1
                        invalidate()
                    }
                    (1.00f < calculatorRightPos && calculatorRightPos <= 2.00f) -> {
                        val pos1 = (((1.00f - 1.00f) / 3f) + 1f) * halfWidth
                        currentPos = pos1
                        onUpdateSpeedUp(1.00f)
                        viewStart = pos1
                        invalidate()
                    }
                    (2.00f < calculatorRightPos && calculatorRightPos <= 3.00f) -> {
                        val pos1 = (((2.00f - 1.00f) / 3f) + 1f) * halfWidth
                        currentPos = pos1
                        onUpdateSpeedUp(2.00f)
                        viewStart = pos1
                        invalidate()
                    }
                    (3.00f < calculatorRightPos && calculatorRightPos <= 4.00f) -> {
                        val pos1 = (((3.00f - 1.00f) / 3f) + 1f) * halfWidth
                        currentPos = pos1
                        onUpdateSpeedUp(3.00f)
                        viewStart = pos1
                        invalidate()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap? {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        var width = drawable.intrinsicWidth
        width = if (width > 0) width else 1
        var height = drawable.intrinsicHeight
        height = if (height > 0) height else 1
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}
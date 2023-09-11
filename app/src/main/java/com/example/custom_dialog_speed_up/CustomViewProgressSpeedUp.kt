package com.example.custom_dialog_speed_up

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomViewProgressSpeedUp(context: Context, attributeSet: AttributeSet): View(context,attributeSet) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.makeMeasureSpec(widthMeasureSpec, MeasureSpec.EXACTLY)
        val height = MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.EXACTLY)
        super.onMeasure(width, height)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paintSeekBar = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#FCFCFC")

        }

        //draw seek bar
        canvas?.drawLine(
            0f,height.toFloat() / 2,
            width.toFloat(),
            height.toFloat() / 2,
            paintSeekBar
        )

        //draw flag
        canvas?.drawLine(
            0f,0f,
            0f,height.toFloat(),
            paintSeekBar
        )

        canvas?.drawLine(
            width.toFloat() / 6 * 1f,0f,
            width.toFloat() / 6 * 1f,height.toFloat(),
            paintSeekBar
        )

        canvas?.drawLine(
            width.toFloat() / 6 * 2f,0f,
            width.toFloat() / 6 * 2f,height.toFloat(),
            paintSeekBar
        )

        canvas?.drawLine(
            width.toFloat() / 6 * 3f,0f,
            width.toFloat() / 6 * 3f,height.toFloat(),
            paintSeekBar
        )

        canvas?.drawLine(
            width.toFloat() / 6 * 4f,0f,
            width.toFloat() / 6 * 4f,height.toFloat(),
            paintSeekBar
        )

        canvas?.drawLine(
            width.toFloat() / 6 * 5f,0f,
            width.toFloat() / 6 * 5f,height.toFloat(),
            paintSeekBar
        )


        canvas?.drawLine(
            width.toFloat(),0f,
            width.toFloat(),height.toFloat(),
            paintSeekBar
        )
    }
}
package com.vimeo.android.networking.roundedcornertoolbar.utils

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.vimeo.android.networking.roundedcornertoolbar.R

class NumberDrawable(context: Context) : Drawable() {
    private val paint: Paint
    private val text: Paint
    private val mTxtRect = Rect()
    private var numb = ""
    private var doDraw = false
    override fun draw(canvas: Canvas) {
        if (!doDraw) {
            return
        }
        val bounds = bounds
        val width = bounds.right - bounds.left.toFloat()
        val height = bounds.bottom - bounds.top.toFloat()
        val radius = Math.max(width, height) / 2 / 2
        val centerX = width - radius - 1 + 5
        val centerY = radius - 5
        if (numb.length <= 2) {
            // circle.
            canvas.drawCircle(centerX, centerY, (radius + 5.5).toFloat(), paint)
        } else {
            canvas.drawCircle(centerX, centerY, (radius + 6.5).toFloat(), paint)
        }
        // text inside the circle.
        text.getTextBounds(numb, 0, numb.length, mTxtRect)
        val textHeight = mTxtRect.bottom - mTxtRect.top.toFloat()
        val textY = centerY + textHeight / 2f
        if (numb.length > 2) canvas.drawText(
            "99+",
            centerX,
            textY,
            text
        ) else canvas.drawText(numb, centerX, textY, text)
    }

    fun setCount(count: String) {
        numb = count

        // if 0, do not show
        doDraw = !count.equals("0", ignoreCase = true)
        invalidateSelf()
    }

    override fun setAlpha(alpha: Int) {
        //
    }

    override fun setColorFilter(cf: ColorFilter?) {
        //
    }

    override fun getOpacity(): Int {
        return PixelFormat.UNKNOWN
    }

    init {
        val mTextSize =
            context.resources.getDimension(R.dimen.badge_count_textsize)
        paint = Paint()
        paint.color = ContextCompat.getColor(context.applicationContext, R.color.red)
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        text = Paint()
        text.color = Color.WHITE
        text.typeface = Typeface.DEFAULT
        text.textSize = mTextSize
        text.isAntiAlias = true
        text.textAlign = Paint.Align.CENTER
    }
}
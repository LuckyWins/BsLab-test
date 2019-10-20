package ru.bslab.test.util

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.ColorMatrixColorFilter
import android.view.View
import android.widget.TextView

fun String.getColorFilter(): ColorFilter {
    return Color.parseColor(this).getColorFilter()
}

fun Int.getColorFilter(): ColorFilter {
    val red = (this and 0xFF0000) / 0xFFFF
    val green = (this and 0xFF00) / 0xFF
    val blue = this and 0xFF

    val matrix = floatArrayOf(
        0f, 0f, 0f, 0f, red.toFloat(),
        0f, 0f, 0f, 0f, green.toFloat(),
        0f, 0f, 0f, 0f, blue.toFloat(),
        0f, 0f, 0f, 1f, 0f
    )

    return ColorMatrixColorFilter(matrix)
}

fun TextView.textColor(color: String) {
    this.setTextColor(Color.parseColor(color))
}

fun View.backgroundColor(color: String) {
    this.setBackgroundColor(Color.parseColor(color))
}
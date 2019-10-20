package ru.bslab.test.util

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.util.AttributeSet
import timber.log.Timber
import kotlin.math.roundToInt

object ViewUtil {

    fun pxToDp(px: Float): Float {
        val densityDpi = Resources.getSystem().displayMetrics.densityDpi.toFloat()
        return px / (densityDpi / 160f)
    }

    fun dpToPx(dp: Int): Int {
        val density = Resources.getSystem().displayMetrics.density
        return (dp * density).roundToInt()
    }
}

fun AttributeSet.obtainStyledAttributes(context: Context, attrs: IntArray, execute: (TypedArray) -> Unit) {
    obtainStyledAttributes(context, attrs, 0, 0, execute)
}

fun AttributeSet.obtainStyledAttributes(context: Context, attrs: IntArray, defStyleAttr: Int, defStyleRes: Int, execute: (TypedArray) -> Unit) {
    var ta: TypedArray? = null
    try {
        ta = context.obtainStyledAttributes(this, attrs, defStyleAttr, defStyleRes)
        ta?.let { execute.invoke(it) }
    } catch (e: Exception) {
        Timber.e(e.localizedMessage.orEmpty())
    } finally {
        ta?.recycle()
    }
}
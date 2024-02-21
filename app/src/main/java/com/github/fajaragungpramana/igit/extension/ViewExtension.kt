package com.github.fajaragungpramana.igit.extension

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup

fun Float.dp(context: Context) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics)
        .toInt()

fun View.setMargins(start: Float, top: Float, end: Float, bottom: Float) {
    val layoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    val startDp = start.dp(this.context)
    val topDp = top.dp(this.context)
    val endDp = end.dp(this.context)
    val bottomDp = bottom.dp(this.context)

    layoutParams.setMargins(startDp, topDp, endDp, bottomDp)
    this.layoutParams = layoutParams
}
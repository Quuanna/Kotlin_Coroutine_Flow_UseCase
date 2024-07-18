package com.anna.usecase_coroutine_and_test.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.anna.usecase_coroutine_and_test.R

/**
 * Extension method to show toast for Context.
 */
fun Context?.toast(text: CharSequence, duration: Int = Toast.LENGTH_LONG) =
    this?.let { Toast.makeText(it, text, duration).show() }


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}


fun ImageView.setDrawable(imageId: Int) {
    if (context != null) {
        setImageDrawable(ContextCompat.getDrawable(context, imageId))
    }
}

package com.example.whatsapp.utils

import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat


fun TextView.setLeftDrawableIconAndColour(@DrawableRes id: Int = 0, @ColorRes colorRes: Int = 0) {
    val drawable = ContextCompat.getDrawable(this.context, id)
    drawable?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
        ContextCompat.getColor(this.context, colorRes),
        BlendModeCompat.SRC_ATOP
    )
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
}
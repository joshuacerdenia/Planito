package com.cerdenia.android.planito.extensions

import android.util.TypedValue
import android.view.View

fun View.addRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
    setBackgroundResource(resourceId)
}
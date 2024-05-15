package dev.farad2020.planeticketseller.ui.base

import android.view.View

fun View.visible(isVisible: Boolean, visibilityType: Int = View.INVISIBLE) {
    visibility = if (isVisible) View.VISIBLE else visibilityType
}

fun View.gone(isGone: Boolean, visibilityType: Int = View.VISIBLE) {
    visibility = if (isGone) View.GONE else visibilityType
}
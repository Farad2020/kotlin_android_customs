package kz.farad2020.repoloader.ui.base

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun Fragment.hideKeyboard() {
    val view = view ?: return
    val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
}

fun showSnackbar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        .setAction("OK") {
            // You can add a retry action or handle other behaviors
        }
        .show()
}
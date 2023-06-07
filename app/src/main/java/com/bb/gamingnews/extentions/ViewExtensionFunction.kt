package com.bb.gamingnews.extentions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

// Get Activity Context from Any View
fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

// Get Activity Context from Any View
fun Context.getParentActivity(): AppCompatActivity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

// Do Back from Any View
fun View.doBack() {
    setOnClickListener {
        if (this.context is Activity) {
            val activity = this.context as Activity
            activity.onBackPressed()
        }
    }
}


// To clear Livedata Values
fun MutableLiveData<String>.doClear() {
    this.value = ""
}

// Visibility
fun View.doGone() {
    visibility = View.GONE
}

// Visibility
fun View.doInvisible() {
    visibility = View.INVISIBLE
}

fun View.doVisible() {
    visibility = View.VISIBLE
}

// To Show Toast
var toast: Toast? = null
fun AppCompatActivity.showToast(message: String) {
    if (toast != null) toast!!.cancel()
    toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast?.show()
}

// To Show Toast
fun showToast(message: String, context: Context) {
    if (toast != null) toast!!.cancel()
    toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    toast?.show()
}

fun Fragment.showToast(message: String) {
    if (toast != null) toast!!.cancel()
    toast = Toast.makeText(this.activity, message, Toast.LENGTH_SHORT)
    toast?.show()
}

// To Get String from View
fun EditText.getString(): String {
    return this.text.toString().trim()
}

fun TextView.getString(): String {
    return this.text.toString().trim()
}

fun String.getString(): String {
    return this.trim()
}
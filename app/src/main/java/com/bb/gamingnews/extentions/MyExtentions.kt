package com.bb.gamingnews.extentions

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.bb.gamingnews.R
import com.bb.gamingnews.api.baseModel.ErrorBean
import com.bb.gamingnews.base.dialog.ProgressDialog
import com.bb.gamingnews.utils.PreferenceManager
import com.bb.gamingnews.base.callback.AlertDialogCallback
import com.bb.gamingnews.base.callback.ListDialogCallback
import retrofit2.HttpException


// To get Gson Instance
var gson: Gson? = null

fun getGsonInstance(): Gson {

    if (gson == null)
        gson = Gson()
    return gson!!
}


fun mGetErrorMessage(exception: HttpException): String {
    var str = ""
    str = try {
        val errorBody: ErrorBean = getGsonInstance().fromJson(
            exception.response()?.errorBody()?.charStream(),
            ErrorBean::class.java
        )
        errorBody.ErrorDetails
    } catch (e: java.lang.Exception) {
        "Some Exception Occurred"
    }
    return str
}

private var progressDialog: ProgressDialog? = null

fun showProgressDialog(context: Context, message: String?) {
    if (!context.getParentActivity()?.isFinishing!! && !context.getParentActivity()?.isDestroyed!!) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context)
        }
        progressDialog!!.setTitle("Please Wait")
        progressDialog!!.setMessage(message)
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }
}

fun hideProgressDialog(context: Context) {
    if (!context.getParentActivity()?.isFinishing!! && !context.getParentActivity()?.isDestroyed!!) {
        progressDialog?.dismiss()
    }
}

fun FragmentActivity.showListDialog(
    list: List<String?>,
    dialogId: Int,
    callback: ListDialogCallback
) {
    if (this.isFinishing && !this.isDestroyed) {
        val dialogAdapter: ArrayAdapter<String?> =
            ArrayAdapter(this, android.R.layout.select_dialog_singlechoice, list)
        val dialogBuilder =
            AlertDialog.Builder(this)
                .setAdapter(
                    dialogAdapter
                ) { _: DialogInterface?, which: Int ->
                    callback.onItemSelected(
                        which,
                        list[which],
                        dialogId
                    )
                }
        dialogBuilder.setTitle(null)
        val alertDialog = dialogBuilder.create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setOnDismissListener {
            callback.onDismiss(
                dialogId
            )
        }
        alertDialog.show()
    }
}

fun FragmentActivity.showListDialog(
    list: List<String?>,
    title: String?,
    dialogId: Int,
    callback: ListDialogCallback
) {
    if (!this.isFinishing && !this.isDestroyed) {
        val dialogAdapter: ArrayAdapter<String?> =
            ArrayAdapter(this, android.R.layout.select_dialog_singlechoice, list)
        val dialogBuilder =
            AlertDialog.Builder(this)
                .setAdapter(
                    dialogAdapter
                ) { _: DialogInterface?, which: Int ->
                    callback.onItemSelected(
                        which,
                        list[which],
                        dialogId
                    )
                }
        dialogBuilder.setTitle(null)
        val alertDialog = dialogBuilder.create()
        alertDialog.setTitle(title)
        alertDialog.setOnDismissListener {
            callback.onDismiss(
                dialogId
            )
        }
        alertDialog.show()
    }
}

fun FragmentActivity.showAlert(
    title: String,
    message: String,
    dialogId: Int,
    callback: AlertDialogCallback
) {
    if (!this.isFinishing && !this.isDestroyed) {
        AlertDialog.Builder(this)
            .setTitle(if (title.isNotEmpty()) title else null)
            .setMessage(if (message.isNotEmpty()) message else null)
            .setCancelable(false)
            .setPositiveButton(
                "ok"
            ) { _: DialogInterface?, _: Int ->
                callback.onPositiveButton(
                    dialogId
                )
            }
            .setNegativeButton(
                "Cancel"
            ) { _: DialogInterface?, _: Int ->
                callback.onNegativeButton(
                    dialogId
                )
            }
            .create()
            .show()
    }
}

fun FragmentActivity.showAlert(
    title: String,
    message: String,
    dialogId: Int,
    positiveButton: String?,
    callback: AlertDialogCallback
) {
    if (!this.isFinishing && !this.isDestroyed) {
        AlertDialog.Builder(this)
            .setTitle(if (title.isNotEmpty()) title else null)
            .setMessage(if (message.isNotEmpty()) message else null)
            .setCancelable(false)
            .setNegativeButton(
                positiveButton
            ) { _: DialogInterface?, _: Int ->
                callback.onPositiveButton(
                    dialogId
                )
            }
            .create()
            .show()
    }
}

fun FragmentActivity.showAlert(title: String, message: String) {
    if (!this.isFinishing && !this.isDestroyed) {
        AlertDialog.Builder(this)
            .setTitle(if (title.isNotEmpty()) title else null)
            .setMessage(if (message.isNotEmpty()) message else null)
            .setCancelable(false)
            .setPositiveButton("OK", null)
            .create()
            .show()
    }
}

fun View.showSnackBar(message: String?) {
    if (!context.getParentActivity()?.isFinishing!! && !context.getParentActivity()?.isDestroyed!!) {
        val snackbar = Snackbar.make(
            this,
            message!!,
            Snackbar.LENGTH_SHORT
        )
        snackbar.view.setBackgroundColor(resources.getColor(R.color.white))
        val textView = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
        textView.setTextColor(resources.getColor(R.color.black))
        snackbar.show()
    }
}


fun FragmentActivity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}


fun getPreferences(context: Context): PreferenceManager {
    return PreferenceManager.getInstance(context)
}



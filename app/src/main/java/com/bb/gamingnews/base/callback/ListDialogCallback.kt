package com.bb.gamingnews.base.callback

interface ListDialogCallback {
    fun onItemSelected(which: Int?, selectedData: String?, dialogId: Int?)
    fun onDismiss(dialogId: Int?)
}
package com.bb.gamingnews.utils.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import com.bb.gamingnews.R

class GeneralDialog(context: Context,var type:String?,var addtext:String?) : Dialog(context, R.style.Custom_Dialog) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.affer_design)
        var heading=findViewById<TextView>(R.id.heading)
        var text=findViewById<TextView>(R.id.text)
        heading.setText(type)
        text.setText(addtext)

    }
}
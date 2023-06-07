package com.bb.gamingnews.utils

import android.os.Build
import android.text.Html

class RemoveHtmlTags {
    companion object
    {
        fun stripHtml(html: String?): String? {
             if (html!=null){
                 return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                     Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString()
                 } else {
                     Html.fromHtml(html).toString()
                 }
             }

           return  "N/A"
        }
    }
}
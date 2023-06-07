package com.bb.gamingnews.utils.share

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView

class ShareUrl {

    companion object
    {
         fun deeplinkingUrl(context: Context,title:String ,img: ImageView,link:String)
        {
//..................................create link ..................................................

            val clipboard =context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Short Link", link)
            clipboard.setPrimaryClip(clip)

//            if (type == 0) {
//                val clip = ClipData.newPlainText("Link", createLinkViewModel.dynamicLink.value)
//                clipboard.setPrimaryClip(clip)
//            } else {
//                val clip = ClipData.newPlainText("Short Link", createLinkViewModel.shortLink.value)
//                clipboard.setPrimaryClip(clip)
//            }


            if(img.drawable==null)
            {
//                val  bitmapDrawable = img.drawable as BitmapDrawable
//                val bitmap =bitmapDrawable.bitmap
//                val bitmpaPath=
//                    MediaStore.Images.Media.insertImage(context.contentResolver,bitmap,"Rohit","")
//                val bitmapUri= Uri.parse(bitmpaPath)
                val sendIntents = Intent(Intent.ACTION_SEND)
//                sendIntents.type = "image/*"
//                sendIntents.putExtra(Intent.EXTRA_STREAM, (bitmapUri))
                sendIntents.putExtra(
                    Intent.EXTRA_TEXT,title+"\n"+link
//                "http://admin.gamingnews247.com//?type=${type}&id=$Id"
//                "com.gamingnews247share://?type=${type}&id=$Id"
                )
//            sendIntents.putExtra(
//                Intent.EXTRA_TEXT,"Rohit deeplinking..."
//            )
                sendIntents.type = "text/plain"
                context.startActivity(Intent.createChooser(sendIntents,"Share Event in OPN"))
                Log.v("behjfbjf",">>>Empty hai ")
            }
            else{
                val  bitmapDrawable = img.drawable as BitmapDrawable
                val bitmap =bitmapDrawable.bitmap
                val bitmpaPath=
                    MediaStore.Images.Media.insertImage(context.contentResolver,bitmap,"Rohit","")
                val bitmapUri= Uri.parse(bitmpaPath)
                val sendIntents = Intent(Intent.ACTION_SEND)
                sendIntents.type = "image/*"
                sendIntents.putExtra(Intent.EXTRA_STREAM, (bitmapUri))
                sendIntents.putExtra(
                    Intent.EXTRA_TEXT,title+"\n"+link
//                "http://admin.gamingnews247.com//?type=${type}&id=$Id"
//                "com.gamingnews247share://?type=${type}&id=$Id"
                )
//            sendIntents.putExtra(
//                Intent.EXTRA_TEXT,"Rohit deeplinking..."
//            )
                sendIntents.type = "text/plain"
                context.startActivity(Intent.createChooser(sendIntents,"Share Event in OPN"))
            }

        }
    }

}
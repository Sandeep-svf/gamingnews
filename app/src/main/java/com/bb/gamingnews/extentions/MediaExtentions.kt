package com.bb.gamingnews.extentions

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@Throws(IOException::class)
fun createImageFile(context: Context): File {
    lateinit var currentPhotoPath: String
    val timeStamp: String =
        SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(
        "JPEG_${timeStamp}_",
        ".jpg",
        storageDir
    ).apply {
        currentPhotoPath = absolutePath
    }
}

fun getPathFromURI(context: Context, contentUri: Uri): String? {
    var res: String? = null
    val proj = arrayOf(MediaStore.Images.Media.DATA)
    val cursor =
        context.contentResolver.query(contentUri, proj, null, null, null)
    if (Objects.requireNonNull(cursor)!!.moveToFirst()) {
        val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        res = cursor.getString(column_index)
    }
    cursor!!.close()
    return res
}





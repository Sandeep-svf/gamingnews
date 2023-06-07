package com.bb.gamingnews.binidngadapters

import android.graphics.drawable.Drawable
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.bb.gamingnews.R
import com.bb.gamingnews.api.Constants
import com.bb.gamingnews.extentions.getParentActivity
import com.bb.gamingnews.extentions.getString


@BindingAdapter("myAdapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
    view.adapter = adapter
}

@BindingAdapter("error")
fun setMyError(view: EditText, msg: String?) {
    view.error = msg
    view.requestFocus(view.getString().length)
}

@BindingAdapter(value = ["url", "placeholder", "circle"], requireAll = false)
fun setUrl(view: ImageView, url1: String?, placeholder: Drawable?, circle: Boolean) {
    if (url1.isNullOrEmpty()) {
        if (placeholder != null) {
            view.setImageDrawable(placeholder)
            return
        } else
            return
    }
    var url = url1
    if (!url.contains("http")) {
        url = "${Constants.BASE_URL}$url"
    }
    if (url.contains(".pdf") || url.contains(".PDF")) {
        if (circle)
            Glide.with(view).load(R.mipmap.ic_launcher).circleCrop().into(view)
        else
            Glide.with(view).load(R.mipmap.ic_launcher).into(view)
    } else if (placeholder != null) {
        if (circle)
            Glide.with(view).load(url).circleCrop().placeholder(placeholder).into(view)
        else
            Glide.with(view).load(url).placeholder(placeholder).into(view)
    } else {
        if (circle)
            Glide.with(view).load(url).circleCrop().into(view)
        else
            Glide.with(view).load(url).into(view)
    }
}

@BindingAdapter("show")
fun show(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) VISIBLE else GONE
}


@BindingAdapter("visible")
fun doVisible(view: View, isVisible: MutableLiveData<Boolean>?) {
    val parentActivity = view.getParentActivity()
    parentActivity?.let {
        isVisible?.observe(it, {
            view.visibility = if (isVisible.value!!) VISIBLE else GONE
        })
    }
}


@BindingAdapter("toast")
fun showToast(view: View, toast: MutableLiveData<String>?) {
    view.getParentActivity()?.let {
        toast?.observe(it, { it1 ->
            Toast.makeText(view.context, it1, Toast.LENGTH_SHORT).show()
        })
    }
}


@BindingAdapter("showToast")
fun showSnackBar(view: View, snackbar: MutableLiveData<String>?) {
    view.getParentActivity()?.let {
        snackbar?.observe(it, { it1 ->
            val snackbar = Snackbar.make(
                view,
                it1!!,
                Snackbar.LENGTH_SHORT
            )
            snackbar.view.setBackgroundColor(view.resources.getColor(R.color.white))
            val textView = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
            textView.setTextColor(view.resources.getColor(R.color.black))
            snackbar.show()
        })
    }
}


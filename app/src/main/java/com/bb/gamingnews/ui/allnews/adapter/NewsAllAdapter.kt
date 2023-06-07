package com.bb.gamingnews.ui.allnews.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.allnews.model.NewsResponceModels
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view_interview.view.*
import kotlinx.android.synthetic.main.item_view_news_vertical.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

class NewsAllAdapter(var context: Context,
                     var callback:Callback,
                     var listTexts: List<NewsResponceModels.Data.LstNew>) :
    RecyclerView.Adapter<NewsAllAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view_news_vertical,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listTexts.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(position)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun setData(position: Int) {


            Glide.with(context).load(listTexts[position].imageUrl)
                .placeholder(R.drawable.space_transparent).into(itemView.imgnewsvertical)
            itemView.txtTitle.setText(listTexts[position].headline)
            itemView.txtCreateds.setText(listTexts[position].createdBy)
            val date = listTexts[position].createdDate
            itemView.txtCreated.setText(listTexts[position].createdBy)

            val c = Calendar.getInstance().time
            val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val formattedDate = df.format(c)
            val date1 = LocalDateTime.parse(date)
            val date2 = LocalDateTime.parse(formattedDate)

            val days = ChronoUnit.DAYS.between(date1, date2)
            itemView.txtdayss.setText(days.toString() + " " + "Days Ago")


        }
    }


    interface Callback {
        fun onItemClicked(favouriteId: Int?)
    }



}
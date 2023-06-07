package com.bb.gamingnews.ui.dashboard.adapter

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.dashboard.Models.DashboardResponceModel
import com.bb.gamingnews.utils.DateUtil
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view_interview.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*

class InterviewAdapter(var context: Context,
                       var callback:Callback,
                       var listTexts: List<DashboardResponceModel.Data.LstInterview>)
    : RecyclerView.Adapter<InterviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view_interview,
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
            Glide.with(context).load(listTexts[position].videoThumbnailUrl).placeholder(R.drawable.images_error).into(itemView.imgInterview)
            itemView.txtTitleInterview.setText(listTexts[position].title)
            itemView.setOnClickListener {
                callback.onItemClicked(position)

            }

            val date=listTexts[position].createdDate
            itemView.txtCreated.setText(listTexts[position].createdBy)

            val c = Calendar.getInstance().time
            val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val formattedDate = df.format(c)
            val date1 = LocalDateTime.parse(date)
            val date2 = LocalDateTime.parse(formattedDate)

           val days =ChronoUnit.DAYS.between(date1, date2)

           if(days.toInt()<=0)
           {
               itemView.txtdays.setText("Today")
           }
           else if(days.toInt()<=7)
            {
                itemView.txtdays.setText(days.toString()+" "+"Days Ago")
            }
            else
            {

                itemView.txtdays.setText(DateUtil.getApiDateFromCalender(listTexts[position!!].createdDate))
            }

            Log.d("rrrrrrrrrrrrrrrr", "" + days)


//            getDaysBetweenDates(formattedDate,date,"MMM dd, yyyy")
//            Log.v("rrrrrrrrrrrrrrrr",""+ getDaysBetweenDates(formattedDate,date,"MMM dd, yyyy"))
        }

//        init {
//            itemView.imageDelete.setOnClickListener {
//                callback.onItemClicked(listMovies[adapterPosition].favouriteId);
//
//            }
//
//
//
//        }
//
//        fun setData(position: Int) {
//            itemView.textMovieTitle.text = listMovies[position].title
//            itemView.textMovieGenre.text = listMovies[position].genres
//            itemView.textMovieLanguage.text = listMovies[position].summary
//
//            Glide.with(itemView.movieImage)
//                .load(listMovies[position].contentImage)
//                .into(itemView.movieImage)
//
//        }

    }


    interface Callback {
        fun onItemClicked(favouriteId: Int?)
    }

    fun getDaysBetweenDates(firstDateValue: String, secondDateValue: String?, format: String): String
    {
        val sdf = SimpleDateFormat(format, Locale.getDefault())

        val firstDate = sdf.parse(firstDateValue)
        val secondDate = sdf.parse(secondDateValue)

        if (firstDate == null || secondDate == null)
            return 0.toString()

        return (((secondDate.time - firstDate.time) / (1000 * 60 * 60 * 24)) + 1).toString()
    }

}
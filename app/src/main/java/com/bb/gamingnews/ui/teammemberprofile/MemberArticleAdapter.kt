package com.bb.gamingnews.ui.teammemberprofile

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.feed.article.models.GetAllArticlesResponceModel
import com.bb.gamingnews.utils.DateUtil
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view_member_article.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*


class MemberArticleAdapter (var context: Context, var callBack: MemberArticleAdapter.Callback,
                            var listTexts: List<GetAllArticlesResponceModel.Data.LstArticle>): RecyclerView.Adapter<MemberArticleAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view_member_article,
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







//
@RequiresApi(Build.VERSION_CODES.O)
fun setData(position: Int) {
            itemView.textView76.text = listTexts[position].createdBy
    if(listTexts[position].createdDate!=null)
    {
        itemView.textView77.text = getDiffrentTwoDate(listTexts[position].createdDate).toString()+" days ago"

    }
            itemView.textView78.text = listTexts[position].title
            itemView.textView79.text = listTexts[position].description
            itemView.txtcommentcount.text = listTexts[position].CommentCount.toString()+" Comments"
            itemView.txtlikeCount.text = listTexts[position].LikeCount.toString()+" likes"
//            itemView.textMovieGenre.text = listMovies[position].genres
//            itemView.textMovieLanguage.text = listMovies[position].summary
//
    itemView.setOnClickListener {
        callBack.onItemClicked(position,
            listTexts[position].id.toString(),

            listTexts[position].attachmentUrl,
            listTexts[position].articleContent,
            listTexts[position].createdBy,
            DateUtil.getApiDateFromCalender(listTexts[position].createdDate)!!,
            listTexts[position].title)
    }
            Glide.with(context)
                .load(listTexts[position].attachmentUrl)
                .into(itemView.imgarical)

        }

    }


    interface Callback {




        fun onItemClicked(favouriteId: Int?,articalId:String,
                          image:String,
                          desc:String,
                          createdBy:String,
                          date:String,
                          title:String)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDiffrentTwoDate(date:String):String {
//    val date=listTexts[position].createdDate

        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val formattedDate = df.format(c)
        val date1 = LocalDateTime.parse(date)
        val date2 = LocalDateTime.parse(formattedDate)

        val days = ChronoUnit.DAYS.between(date1, date2)
        val minut = ChronoUnit.MINUTES.between(date1, date2)
//    itemView.txtdays.setText(days.toString()+" "+"Days Ago")

        Log.d("rrrrrrrrrrrrrrrr", "" + days)
        return days.toString()
    }
}
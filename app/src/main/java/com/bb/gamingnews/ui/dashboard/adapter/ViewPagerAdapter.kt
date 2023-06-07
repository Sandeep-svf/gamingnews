package com.bb.gamingnews.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.dashboard.Models.DashboardResponceModel
import com.bb.gamingnews.utils.DateUtil
import com.bb.gamingnews.utils.RemoveHtmlTags
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.image_slider_itemdash.view.*

class ViewPagerAdapter(
     val context:Context, val callback:CallbackTrending,
    var listTexts: List<DashboardResponceModel.Data.LstNew>) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.image_slider_itemdash,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listTexts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(position)


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(position: Int) {
//                itemView.imageView.setImageResource(listTexts[position])
            Glide.with(context).load(listTexts[position].imageUrl).placeholder(R.drawable.space_transparent).into(itemView.imgMain)

            itemView.txtheading.setText(RemoveHtmlTags.stripHtml(listTexts[position].headline)+": "+"\n"+(RemoveHtmlTags.stripHtml(listTexts[position].newsDescription)))
            itemView.txtCreate.setText(listTexts[position].createdBy)
            itemView.txtDate.setText(DateUtil.getApiDateFromCalender(listTexts[position].createdDate));

                itemView.setOnClickListener {
                    callback.onItemClickedTreding(position)
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
        }

    }


    interface CallbackTrending {
        fun onItemClickedTreding(favouriteId: Int?)
    }


}
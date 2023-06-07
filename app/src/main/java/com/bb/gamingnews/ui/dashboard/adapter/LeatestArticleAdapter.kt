package com.bb.gamingnews.ui.dashboard.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.dashboard.Models.DashboardResponceModel
import com.bb.gamingnews.utils.DateUtil
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view_leatestarticle.view.*

class LeatestArticleAdapter(var context:Context,var callback:LatArticalsCallback,var listTexts: List<DashboardResponceModel.Data.LstArticle>) : RecyclerView.Adapter<LeatestArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view_leatestarticle,
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
            Glide.with(context)
                .load(listTexts[position].attachmentUrl)
                .into(itemView.imgArtical)

            itemView.txtTitleArti.text = listTexts[position].title
            itemView.txtCreat.text = listTexts[position].createdBy

            Log.v("datewssss",""+listTexts[position].date)
            itemView.setOnClickListener {

                callback.onLatArticalItemClick( position,
                    listTexts[position].id.toString(),
                    listTexts[position].attachmentUrl!!,
                    listTexts[position].articleContent!!,
                    listTexts[position].createdBy!!,
                    DateUtil.getApiDateFromCalender(listTexts[position].date)!!,
                    listTexts[position].title!!)
            }

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


    interface LatArticalsCallback {
        fun onLatArticalItemClick(favouriteId: Int?,
                                  articalId:String,
                                  image:String,
                                  desc:String,
                                  createdBy:String,
                                  date:String,
                                  title:String)
    }

}
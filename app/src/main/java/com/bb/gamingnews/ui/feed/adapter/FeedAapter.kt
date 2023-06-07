package com.bb.gamingnews.ui.feed.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.allnews.model.NewsResponceModels
import com.bb.gamingnews.utils.DateUtil
import com.bb.gamingnews.utils.GlideApp
import kotlinx.android.synthetic.main.item_view_feed.view.*

class FeedAapter(var context: Context, var callback: FeedAapter.Callback,
                 var listTexts: List<NewsResponceModels.Data.LstNew>) : RecyclerView.Adapter<FeedAapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view_feed,
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
                GlideApp.with(itemView.imgnews)
                    .load(listTexts[position].imageUrl).placeholder(R.drawable.placeholder)
                    .into(itemView.imgnews)
//                Log.v("2adsfsdfssdsf",listTexts[position].imageUrl!!)

                itemView.txtTitles.setText(listTexts[position].headline)
                itemView.txtcreatedd.setText(listTexts[position].createdBy)
                itemView.txtDatess.setText(DateUtil.getApiDateFromCalender(listTexts[position].createdDate))
                itemView.setOnClickListener {
                    callback.onNewsItemClicked(position)
                }



//            itemView.imageDelete.setOnClickListener {
//                callback.onItemClicked(listMovies[adapterPosition].favouriteId);
//
//            }

            itemView.cardView3.setOnClickListener {

                val args = Bundle()
                args.putString("type","News")
                args.putString("id",listTexts[position].id.toString())
                args.putString("Image",listTexts[position].imageUrl)
                args.putString("Headline",listTexts[position].headline)
                args.putString("Creted",listTexts[position].createdBy)
                args.putString("date",DateUtil.getApiDateFromCalender(listTexts[position].createdDate))
                args.putString("desc",listTexts[position].newsDescription)
                itemView. findNavController().navigate(R.id.newsDetailFragment,args)
            }
        }
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
        fun onNewsItemClicked(favouriteId: Int?)
    }



}
package com.bb.gamingnews.ui.feed.interview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.feed.interview.model.GetAllInterviewResponceModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view_new_horizontal.view.*

class NewsAdapterHorizontal(var context:Context,
                            var callback: NewsAdapterHorizontal.Callback,
                            var listTexts: List<GetAllInterviewResponceModel.Data>) : RecyclerView.Adapter<NewsAdapterHorizontal.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view_new_horizontal,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {

        if(listTexts.size>=3)
        {
            return 3

        }
        else
        {
            return listTexts.size

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(position)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(position: Int) {
            Glide.with(context).load(listTexts[position].videoThumbnailUrl).placeholder(R.drawable.space_transparent).into(itemView.imgnewss)

            itemView.txttitle.setText(listTexts[position].title)
            itemView.setOnClickListener {
                callback.NewsonItemClicked(position)
            }

        }


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
        fun NewsonItemClicked(favouriteId: Int?)
    }


}
package com.bb.gamingnews.ui.allvideo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.allvideo.models.AllVideoResponceResult
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.video_adapter.view.*

class AllVideoAdapter(val context:Context, var callback: Callback, var listTexts: List<AllVideoResponceResult.Data.LstVideo>) : RecyclerView.Adapter<AllVideoAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.video_adapter,
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

    inner class ViewHolder(val itemViewss: View) : RecyclerView.ViewHolder(itemViewss) {
        fun setData(position: Int) {
            Glide.with(context).load(listTexts[position].thumbnailImageUrl).placeholder(R.drawable.space_transparent).into(itemViewss.imgVideo)

            itemViewss.txtDescription.setText(listTexts[position].title)
            itemViewss.txtvideoLenth.setText(listTexts[position].videoLength)
            itemViewss.setOnClickListener {
                val title=listTexts[position].title
                val createby=listTexts[position].createdBy
                val views=listTexts[position].viewCount.toString()
                val like=listTexts[position].likeCount.toString()
                val desc=listTexts[position].desc
                val ThumbnailImageUrl=listTexts[position].thumbnailImageUrl
                val VideoUrl=listTexts[position].videoUrl
                val VideoLength=listTexts[position].videoLength


                callback.videoonItemClicked(position,
                    title,
                    createby,
                    views,
                    like,
                    desc,
                    ThumbnailImageUrl,
                    VideoUrl,
                    VideoLength

                )
            }
            itemViewss.txtCreate.setText(listTexts[position].createdBy)


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

        fun videoonItemClicked(favouriteId: Int?,
                               title:String?,
                               createby:String?,
                               views:String,
                               like:String,
                               desc:String?,
                               ThumbnailImageUrl:String?,
                               VideoUrl:String?,
                               VideoLength:String?)

    }

}
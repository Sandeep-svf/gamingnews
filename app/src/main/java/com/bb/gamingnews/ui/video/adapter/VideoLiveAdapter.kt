package com.bb.gamingnews.ui.video.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.allvideo.models.AllVideoResponceResult
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view_video_live.view.*
import kotlinx.android.synthetic.main.item_view_video_live.view.txtcreate
import kotlinx.android.synthetic.main.item_view_video_recent.view.*

class VideoLiveAdapter(var context: Context, var callback: Callback ,
                       var listTexts: List<AllVideoResponceResult.Data.LstVideo>)
    : RecyclerView.Adapter<VideoLiveAdapter.ViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view_video_live,
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
                    .load(listTexts[position].thumbnailImageUrl)
                    .into(itemView.imgcategory)
                itemView.txtTille.setText(listTexts[position].title)
                itemView.txtcreate.setText(listTexts[position].createdBy)
                itemView.txtViews.setText(listTexts[position].viewCount.toString())

                if(listTexts[position].videoLength!=null)
                {
                    itemView.txtvideoLenth.setText( listTexts[position].videoLength.toString())

                }
                else
                {
                    itemView.txtvideoLenth.setText("00.00")

                }
//                itemView.txtvideoLenth.setText(listTexts[position].videoLength.toString())
                itemView.setOnClickListener {

                    val title=listTexts[position].title
                    val createby=listTexts[position].createdBy
                    val views=listTexts[position].viewCount.toString()
                    val like=listTexts[position].likeCount.toString()
                    val desc=listTexts[position].desc
                    val ThumbnailImageUrl=listTexts[position].thumbnailImageUrl
                    val VideoUrl=listTexts[position].videoUrl
                    val VideoLength=listTexts[position].videoLength


                    callback.videoLiveonItemClicked(listTexts[position].id,
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

//
//        }

    }


    interface Callback {
        fun videoLiveonItemClicked(favouriteId: Int?,
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
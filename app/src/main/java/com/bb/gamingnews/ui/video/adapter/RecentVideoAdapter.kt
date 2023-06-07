package com.bb.gamingnews.ui.video.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.allvideo.models.AllVideoResponceResult
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view_video_recent.view.*
import kotlinx.android.synthetic.main.item_view_video_recent.view.txtcreate

class RecentVideoAdapter (var context: Context, var callback: Callback,
                          var listTexts: List<AllVideoResponceResult.Data.LstVideo>):
    RecyclerView.Adapter<RecentVideoAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view_video_recent,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {

        if(listTexts.size>=5)
        {
//            Log.v("45tyuiop","5"+listTexts.size)
            return 5
        }else
        {
//            Log.v("45tyuiop","no")
            return listTexts.size
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(position)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(position: Int) {
            Glide.with(context)
                .load(listTexts[position].thumbnailImageUrl)
                .into(itemView.imageView16)
            itemView.textView25.setText( listTexts[position].title)
            itemView.txtcreate.setText( listTexts[position].createdBy)
            itemView.txtView.setText( listTexts[position].viewCount.toString())
            if(listTexts[position].videoLength!=null)
            {
                itemView.textView24.setText( listTexts[position].videoLength.toString())

            }
            else
            {
                itemView.textView24.setText("00.00")

            }
            itemView.setOnClickListener {

                val title=listTexts[position].title
                val createby=listTexts[position].createdBy
                val views=listTexts[position].viewCount.toString()
                val like=listTexts[position].likeCount.toString()
                val desc=listTexts[position].desc
                val ThumbnailImageUrl=listTexts[position].thumbnailImageUrl
                val VideoUrl=listTexts[position].videoUrl
                val VideoLength=listTexts[position].videoLength


                callback.onItemClickedRecentVideo(listTexts[position].id,
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

//
    }


    interface Callback {
        fun onItemClickedRecentVideo(favouriteId: Int?,
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
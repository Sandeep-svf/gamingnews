package com.bb.gamingnews.ui.Comments.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.Comments.model.GetArticleCommentsResponceModel
import com.bb.gamingnews.utils.DateUtil
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.comment_row_item.view.*

class CommentAdapter (var context: Context,
                      var listTexts: List<GetArticleCommentsResponceModel.Data>):
    RecyclerView.Adapter<CommentAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.comment_row_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {


            return listTexts.size
        Log.v("rtysccccuio",listTexts.size.toString())


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(position)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(position: Int) {
            Glide.with(context)
                .load(listTexts[position].commentedByUserImageUrl).placeholder(R.drawable.placeholder)
                .into(itemView.imageView1)
            itemView.tv_sender.setText(DateUtil.getApiDateFromCalender(listTexts[position].createdDate))
            itemView.mymessageTxt_mine.setText(listTexts[position].comment)
            itemView.txtusername.setText( listTexts[position].commentedBy)
//            itemView.textView24.setText( listTexts[position].videoLength.toString())

        }

//
    }


    interface Callback {
        fun onItemClickedRecentVideo(favouriteId: Int?)
    }



}
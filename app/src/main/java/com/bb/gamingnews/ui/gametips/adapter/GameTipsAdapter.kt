package com.bb.gamingnews.ui.gametips.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.gametips.model.GetGameTipsResponceModel
import kotlinx.android.synthetic.main.gametips_item.view.*

class GameTipsAdapter (var context: Context,
                       var listTexts: List<GetGameTipsResponceModel.Data>):
    RecyclerView.Adapter<GameTipsAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.gametips_item,
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
//            Glide.with(context)
//                .load(listTexts[position].thumbnailImageUrl)
//                .into(itemView.imageView16)
            itemView.txtHead.setText( listTexts[position].heading)
            itemView.newsContent.setText( listTexts[position].content.toString())

        }

//
    }



}
package com.bb.gamingnews.ui.feed.event.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.dashboard.Models.DashboardResponceModel
import com.bb.gamingnews.utils.RemoveHtmlTags
import com.bumptech.glide.Glide
import com.bumptech.glide.util.Util
import kotlinx.android.synthetic.main.event_detail_new_item.view.*


class EventDetailsNewAdapter(var requireContext: Context, var listTexts: List<DashboardResponceModel.Data.LstEvent.LstSubEvent.LstReporting>) : RecyclerView.Adapter<EventDetailsNewAdapter.ViewHolder>() {
    var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.event_detail_new_item,
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

            itemView.txtHead.setText(RemoveHtmlTags.stripHtml(listTexts[position].title))
            itemView.newsContent.setText(RemoveHtmlTags.stripHtml(listTexts[position].details))
            Glide.with(requireContext)
                .load(listTexts[position].imageUrl).placeholder(R.drawable.placeholder).into(itemView.img1)

        }


    }


    interface Callback {
        fun onItemClicked(favouriteId: Int?)
    }
}

package com.bb.gamingnews.ui.feed.event.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.feed.event.Model.AllEventResponceModel
import com.bb.gamingnews.utils.DateUtil
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.live_event_adapter.view.*

class LiveEventAdapter(var requireContext: Context,
                       var callbacks: Callback,
                       var listTexts: List<AllEventResponceModel.Data.LstSubEvent.LstVideo>
) : RecyclerView.Adapter<LiveEventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.live_event_adapter,
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

         itemView.textViewHeading.setText(listTexts[position].title)
            Glide.with(requireContext)
                .load(listTexts[position].thumbnailImageUrl).placeholder(R.drawable.placehold).into(itemView.imga)

         itemView.txtcretename.setText(listTexts[position].createdBy)
         itemView.txtDates.setText(DateUtil.getApiDateFromCalender(listTexts[position].createdDate))
            itemView.setOnClickListener {
                callbacks.onItemClicked(position)

            }
        }


    }


    interface Callback {
        fun onItemClicked(favouriteId: Int?)
    }
}

package com.bb.gamingnews.ui.feed.event.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.extentions.showToast
import com.bb.gamingnews.ui.feed.event.Model.AllEventResponceModel
import com.bb.gamingnews.utils.DateUtil
import kotlinx.android.synthetic.main.subevent_video_inner_adapter.view.*

class SubEventVideoInnerAdapter(var requireContext: Context,
                                var callback:Callbackss,
                                var listTexts: List<AllEventResponceModel.Data.LstSubEvent>) : RecyclerView.Adapter<SubEventVideoInnerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.subevent_video_inner_adapter,
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
            itemView.heading.setText(listTexts[position].title)
            itemView.winning_data.setText(listTexts[position].winningHand)
            itemView.price.setText("Price -₹ "+listTexts[position].priceMoney)
            itemView.description.setText(listTexts[position].winnerName+" win the "+listTexts[position].title)
            itemView.date.setText(DateUtil.getApiDateFromCalender(listTexts[position].startDate)
                    +" - "+DateUtil.getApiDateFromCalender(listTexts[position].endDate))
            itemView.root.setOnClickListener {

                if(listTexts[position].lstReportings!=null)
                {
                    callback.subeventonItemClicked(listTexts[position].lstReportings)

                }
                else
                {
                    showToast("no data found !",requireContext)
                }

//                showToast("sixeeee"+listTexts[position].lstReportings.size.toString(),requireContext)
//                callback.onItemInnerClicked(position)
            }

        }


    }

    interface Callbackss {
        fun subeventonItemClicked(listTexts: List<AllEventResponceModel.Data.LstSubEvent.LstReporting>)
    }
}

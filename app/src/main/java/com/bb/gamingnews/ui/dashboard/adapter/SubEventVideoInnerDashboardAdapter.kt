package com.bb.gamingnews.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.extentions.showToast
import com.bb.gamingnews.ui.dashboard.Models.DashboardResponceModel
import com.bb.gamingnews.utils.DateUtil
import kotlinx.android.synthetic.main.subevent_video_inner_adapter.view.*

class SubEventVideoInnerDashboardAdapter(var requireContext: Context, var callback:Callbacks,
                                         var listTexts: List<DashboardResponceModel.Data.LstEvent.LstSubEvent>) : RecyclerView.Adapter<SubEventVideoInnerDashboardAdapter.ViewHolder>() {

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
            itemView.setOnClickListener {
//                requireContext.findna
//                showToast("ooook",requireContext)
                callback.onItemInnerClicked(listTexts[position].lstReportings!!)
            }
            itemView.heading.setText(listTexts[position].title)
            itemView.winning_data.setText(listTexts[position].winningHand)
            itemView.price.setText("Price -₹ "+listTexts[position].priceMoney)
            itemView.description.setText(listTexts[position].winnerName+" win the "+listTexts[position].title)
            itemView.date.setText(DateUtil.getApiDateFromCalender(listTexts[position].startDate)
                    +" - "+DateUtil.getApiDateFromCalender(listTexts[position].endDate))
            itemView.root.setOnClickListener {
//                showToast("okkkkkk",requireContext)
                if(listTexts[position].lstReportings!=null)
                {
                    callback.onItemInnerClicked(listTexts[position].lstReportings!!)

                }
                else
                {
                    showToast("no data found !",requireContext)
                }
            }

        }


    }


    interface Callbacks{

        fun onItemInnerClicked(listTexts: List<DashboardResponceModel.Data.LstEvent.LstSubEvent.LstReporting>)
    }
}

package com.bb.gamingnews.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.dashboard.Models.DashboardResponceModel
import com.bb.gamingnews.utils.DateUtil
import kotlinx.android.synthetic.main.sub_event_video_adapter.view.*

class OnGoingAdapter(var requireContext: Context, var callback: OnGoingAdapter.Callback,
                     var listTexts: List<DashboardResponceModel.Data.LstEvent>) : RecyclerView.Adapter<OnGoingAdapter.ViewHolder>() {
    var mExpandedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.sub_event_video_adapter,
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


        var adapter= SubEventVideoInnerDashboardAdapter(requireContext,object : SubEventVideoInnerDashboardAdapter.Callbacks{


            override fun onItemInnerClicked(listTexts: List<DashboardResponceModel.Data.LstEvent.LstSubEvent.LstReporting>) {
                callback.subeventonItemClicked(listTexts)


//                if(listTexts!=null)
//                    {
//
//                    }
//                else
//                    {
//                        showToast("no data found !",requireContext)
//                    }
            }

        },listTexts[position].lstSubEvents!!)
        holder.itemView.innerRecyler.adapter = adapter
        val isExpanded = position === mExpandedPosition

        if (isExpanded){
            holder.itemView.arrowIcon.rotation=270f
        }else {
            holder.itemView.arrowIcon.rotation=90f
        }
        holder.itemView.innerRecyler.setVisibility(
            if (isExpanded) {
                View.VISIBLE

            }
            else {
                View.GONE
            }
        )
        holder.itemView.innerRecyler.isActivated = isExpanded
        holder.itemView.arrowIcon.setOnClickListener {
            mExpandedPosition = if (isExpanded) -1 else position
            notifyDataSetChanged()
        }


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(position: Int) {
//            itemView.priceWithDate.setText("Prize : "+listTexts[position].priceMoney+" | "+ DateUtil.getApiDateFromCalender(listTexts[position].createdDate))
//            itemView.priceWithDate.setText(DateUtil.getApiDateFromCalender(listTexts[position].startDate)+" - "+ DateUtil.getApiDateFromCalender(listTexts[position].endDate))
            itemView.priceWithDate.setText(DateUtil.getApiDateFromCalender(listTexts[position].startDate))
            itemView.heading.setText((listTexts[position].title))
            itemView.description.setText((listTexts[position].venue))
//            Glide.with(requireContext)
//                .load(listTexts[position].imageUrl).placeholder(R.drawable.placeholder).into(itemView.imgSub)

//            itemView.setOnClickListener {
//
//            }

        }


    }


    interface Callback {
        fun subeventonItemClicked(listTexts: List<DashboardResponceModel.Data.LstEvent.LstSubEvent.LstReporting>)
    }


}
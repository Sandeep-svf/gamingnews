package com.bb.gamingnews.ui.feed.event.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.feed.event.Model.AllEventResponceModel
import com.bb.gamingnews.utils.DateUtil
import kotlinx.android.synthetic.main.sub_event_video_adapter.view.*

class SubEventVideoAdapter(var requireContext: Context,
                           var callback:CallbackSub,
                           var listTexts: List<AllEventResponceModel.Data>) :
    RecyclerView.Adapter<SubEventVideoAdapter.ViewHolder>(){
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


        var adapter=SubEventVideoInnerAdapter(requireContext,object :SubEventVideoInnerAdapter.Callbackss{
            override fun subeventonItemClicked(listTexts: List<AllEventResponceModel.Data.LstSubEvent.LstReporting>) {
                callback.subeventonItemClicked(listTexts)
            }


        },listTexts[position]!!.lstSubEvents)
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
//            itemView.priceWithDate.setText(DateUtil.getApiDateFromCalender(listTexts[position].startDate)+" - "+DateUtil.getApiDateFromCalender(listTexts[position].endDate))
            itemView.priceWithDate.setText(DateUtil.getApiDateFromCalender(listTexts[position].startDate))
            itemView.heading.setText((listTexts[position].title))
            itemView.description.setText((listTexts[position].venue))
//            Glide.with(requireContext)
//                .load(listTexts[position].imageUrl).placeholder(R.drawable.placeholder).into(itemView.imgSub)

            itemView.setOnClickListener {

            }

        }



    }

    interface CallbackSub {
        fun subeventonItemClicked(listTexts: List<AllEventResponceModel.Data.LstSubEvent.LstReporting>)
    }



}

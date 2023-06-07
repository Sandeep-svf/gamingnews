package com.bb.gamingnews.ui.notification.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.notification.model.GetSentNotification
import com.bb.gamingnews.utils.DateUtil
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.notification_inner_adapter.view.*

class NotificationInnerAdapter(var context:Context,var listTexts: List<GetSentNotification.Data>) : RecyclerView.Adapter<NotificationInnerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationInnerAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.notification_inner_adapter,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: NotificationInnerAdapter.ViewHolder, position: Int) {
        holder.setData(position)

    }

    override fun getItemCount(): Int {
        return listTexts.size
    }
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun setData(position: Int) {
                itemView.txtHeading.text= listTexts[position].title
                var date=DateUtil.getApiDateFromCalender(listTexts[position].createdDate)
                itemView.txtTimes.text= date
                itemView.descriptionTxt.text= listTexts[position].body
                Glide.with(itemView).load(listTexts[position].imageUrl).into(itemView.imgpro)

            }


        }
}
package com.bb.gamingnews.ui.notification.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.notification.SwipeHelper
import com.bb.gamingnews.ui.notification.model.GetSentNotification
import kotlinx.android.synthetic.main.notification_adapter.view.*

class NotificationAdapter(var requireContext: Context,
                          var listTexts: List<GetSentNotification.Data>,var callback: DeleteNotiCallback
) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.notification_adapter,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotificationAdapter.ViewHolder, position: Int) {
        holder.itemView.notificationInnerRecycler.adapter=NotificationInnerAdapter(requireContext,listTexts)


        val itemTouchHelper = ItemTouchHelper(object : SwipeHelper( holder.itemView.notificationInnerRecycler) {
            override fun instantiateUnderlayButton(position: Int): List<UnderlayButton> {
                var buttons = listOf<UnderlayButton>()
                val deleteButton = deleteButton(position)
//                val markAsUnreadButton = markAsUnreadButton(position)
//                buttons = listOf(deleteButton, markAsUnreadButton)
                buttons = listOf(deleteButton)

                return buttons
            }
        })
        itemTouchHelper.attachToRecyclerView(holder.itemView.notificationInnerRecycler)
    }

    override fun getItemCount(): Int {
        return listTexts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



    }



    private fun deleteButton(position: Int) : SwipeHelper.UnderlayButton {
        return SwipeHelper.UnderlayButton(
            requireContext,
            "Delete",
            14.0f,
            R.color.redColor,
            object : SwipeHelper.UnderlayButtonClickListener {
                override fun onClick() {

                    callback.onNotificationClick(listTexts[position].id)
//                    showToast("Deleted item ${listTexts[position].id}",requireContext)


                }
            })
    }

    private fun markAsUnreadButton(position: Int) : SwipeHelper.UnderlayButton {
        return SwipeHelper.UnderlayButton(
            requireContext,
            "Mark as unread",
            14.0f,
            android.R.color.holo_green_light,
            object : SwipeHelper.UnderlayButtonClickListener {
                override fun onClick() {

//                    showToast("Marked as unread item ${listTexts[position].id}",requireContext )
                }
            })
    }

    interface DeleteNotiCallback {
        fun onNotificationClick(NotifiacationId: Int?)
    }

}
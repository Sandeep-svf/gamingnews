package com.bb.gamingnews.ui.feed.event.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import kotlinx.android.synthetic.main.event_category_adapter.view.*
import kotlinx.android.synthetic.main.event_category_adapter.view.view


class EventCategoryAdapter(var requireContext: Context,var listTexts: List<String>) : RecyclerView.Adapter<EventCategoryAdapter.ViewHolder>() {
    var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.event_category_adapter,
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
            if (selectedPosition === position) {
                itemView.view.visibility=View.VISIBLE
                itemView.txtCategorys.setTextColor(requireContext.resources.getColor(R.color.textcoloryello))
            }
        else
            {
                itemView.view.visibility=View.GONE
                itemView.txtCategorys.setTextColor(requireContext.resources.getColor(R.color.grey_Dark))


            }
                itemView.setOnClickListener(View.OnClickListener {
                selectedPosition = position
                notifyDataSetChanged()
            })
            itemView.txtCategorys.setText( listTexts[position])

        }


    }


    interface Callback {
        fun onItemClicked(favouriteId: Int?)
    }
}

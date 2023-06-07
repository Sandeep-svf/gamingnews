package com.bb.gamingnews.ui.feed.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.allnews.model.NewsResponceModels
import kotlinx.android.synthetic.main.event_category_adapter.view.*
import kotlinx.android.synthetic.main.event_category_adapter.view.view

class FeedCategoryAdapter(
    val context:Context, val callback: FeedCategoryAdapter.Callback,
    var listTexts: List<NewsResponceModels.Data>
) : RecyclerView.Adapter<FeedCategoryAdapter.ViewHolder>() {
    var selectedPosition = 0
    var bool:Boolean=false
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)              {

        fun setData(position: Int) {
            if(bool==false)
            {
                bool=true
                callback.onItemClickedNews(0,listTexts.get(0).tag)

            }
            if (selectedPosition === position) {
                itemView.view.visibility=View.VISIBLE
                itemView.txtCategorys.setTextColor( itemView.txtCategorys.resources.getColor(R.color.black))
            }
            else
            {
                itemView.view.visibility=View.GONE
                itemView.txtCategorys.setTextColor( itemView.txtCategorys.resources.getColor(R.color.grey_Dark))


            }

            itemView.setOnClickListener(View.OnClickListener {
                selectedPosition = position
                callback.onItemClickedNews(position,listTexts.get(position).tag)
                notifyDataSetChanged()
            })

            itemView.txtCategorys.setText( listTexts[position].tag)
        }

}


    interface Callback {
        fun onItemClickedNews(favouriteId: Int?,category:String?)
    }



}
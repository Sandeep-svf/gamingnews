package com.bb.gamingnews.ui.video.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.allvideo.models.AllVideoResponceResult
import kotlinx.android.synthetic.main.item_view_article.view.*

class VideoCategoryAdapter(val context:Context,val callback:Callback,
                           var listTexts: List<AllVideoResponceResult.Data>) :
    RecyclerView.Adapter<VideoCategoryAdapter.ViewHolder>() {
    var selectedPosition = 0

    var bool:Boolean=false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view_article,
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
                 if(bool==false)
                 {
                     bool=true
                     callback.onItemClickedCategoryName(0,listTexts.get(0).category)

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
                callback.onItemClickedCategoryName(position,listTexts.get(position).category)
                notifyDataSetChanged()
            })
                 itemView.txtCategorys.setText( listTexts[position].category)
        }
    }


    interface Callback {
        fun onItemClickedCategoryName(favouriteId: Int?,category:String?)
    }




}
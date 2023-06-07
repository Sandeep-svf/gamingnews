package com.bb.gamingnews.ui.gametips.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.gametips.model.GametipCategoryResponceModel
import kotlinx.android.synthetic.main.item_view_article.view.*

class GameCategoryAdapter(val context:Context, val callback:Callback,
                          var listTexts: List<GametipCategoryResponceModel.Data>) :
    RecyclerView.Adapter<GameCategoryAdapter.ViewHolder>() {
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
                     callback.onItemClickedCategoryName(0,listTexts.get(0).id.toString())

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
                callback.onItemClickedCategoryName(position,listTexts.get(position).id.toString())
                notifyDataSetChanged()
            })
                 itemView.txtCategorys.setText( listTexts[position].title)
        }
    }


    interface Callback {
        fun onItemClickedCategoryName(favouriteId: Int?,category:String?)
    }




}
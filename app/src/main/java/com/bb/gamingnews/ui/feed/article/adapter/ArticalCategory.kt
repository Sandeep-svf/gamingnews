package com.bb.gamingnews.ui.feed.article.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.feed.article.models.GetAllArticlesResponceModel
import kotlinx.android.synthetic.main.event_category_adapter.view.txtCategorys
import kotlinx.android.synthetic.main.event_category_adapter.view.view

class ArticalCategory(val context: Context, val callback: ArticalCategory.Callback,
                      var listTexts: List<GetAllArticlesResponceModel.Data>) : RecyclerView.Adapter<ArticalCategory.ViewHolder>() {
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
                callback.onItemClicked(0,listTexts.get(0).tag)

            }
            if (selectedPosition === position) {
                itemView.view.visibility=View.VISIBLE
                itemView.txtCategorys.setTextColor( itemView.txtCategorys.resources.getColor(R.color.textcoloryello))
            }
            else
            {
                itemView.view.visibility=View.GONE
                itemView.txtCategorys.setTextColor( itemView.txtCategorys.resources.getColor(R.color.grey_Dark))


            }
            itemView.setOnClickListener(View.OnClickListener {
                selectedPosition = position
//                showToast("mmmmm",context)
                callback.onItemClicked(position,listTexts.get(position).tag)
                notifyDataSetChanged()
            })
            itemView.txtCategorys.setText( listTexts[position].tag)
        }
//        fun setData(position: Int) {
//            if (selectedPosition === position) {
//                itemView.view.visibility=View.VISIBLE
//                itemView.txtCategorys.setTextColor( itemView.txtCategorys.resources.getColor(R.color.black))
//            }
//            else
//            {
//                itemView.view.visibility=View.GONE
//                itemView.txtCategorys.setTextColor( itemView.txtCategorys.resources.getColor(R.color.grey_Dark))
//
//
//            }
//            itemView.setOnClickListener(View.OnClickListener {
//                selectedPosition = position
//                notifyDataSetChanged()
//            })
//
//            itemView.txtCategorys.setText(listTexts[position].tag)
//        }
    }


    interface Callback {
        fun onItemClicked(favouriteId: Int?,category:String?)
    }


}
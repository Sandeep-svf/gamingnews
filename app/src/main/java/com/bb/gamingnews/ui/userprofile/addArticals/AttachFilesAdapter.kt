package com.bb.gamingnews.ui.userprofile.addArticals

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.userprofile.addArticals.models.GetAllArticleTagsResponceModel
import kotlinx.android.synthetic.main.articales_categories_adapter.view.*
import kotlinx.android.synthetic.main.event_category_adapter.view.*

class AttachFilesAdapter(var context: Context,
                         var callback: ArticalsTagsCallback,
                         var listTexts: List<GetAllArticleTagsResponceModel.Data>
                         ) :RecyclerView.Adapter<AttachFilesAdapter.ViewHolder>() {
    var selectedPosition = 0
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AttachFilesAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.articales_categories_adapter,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AttachFilesAdapter.ViewHolder, position: Int) {
        holder.itemView.text.setText(listTexts.get(position).tag)
        holder.setData(position)

    }

    override fun getItemCount(): Int {
        return listTexts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(position: Int) {
            if (selectedPosition == position) {
                callback.onArticalTagItemClick(listTexts[position].id)
                itemView.text.visibility = View.VISIBLE
                itemView.text.setTextColor(context.resources.getColor(R.color.textcoloryello))
            } else {
                itemView.text.visibility = View.VISIBLE
                itemView.text.setTextColor(context.resources.getColor(R.color.grey_Dark))
            }
            itemView.maincv.setOnClickListener {
                if(selectedPosition!=position){
                    selectedPosition =position
                }
                notifyDataSetChanged()

                callback.onArticalTagItemClick(listTexts[position].id)
                Log.v("hjsdsbcjdssdsssss",">>>>>>>>>>>>>>>>>"+listTexts[position].id)

            }

        }

    }

    interface ArticalsTagsCallback {
        fun onArticalTagItemClick(favouriteId: Int?)
    }

}
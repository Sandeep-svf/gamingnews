package com.bb.gamingnews.ui.userprofile.addArticals

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.account.preferences.GetPrefrencesResponceModel
import kotlinx.android.synthetic.main.articales_categories_adapter.view.*

class ArticalsCategoriesAdapter(var context: Context,var callback: CallBack_pref) : RecyclerView.Adapter<ArticalsCategoriesAdapter.ViewHolder>() {


    var Orderlist = mutableListOf<GetPrefrencesResponceModel.Data>()
    fun setData (list: List<GetPrefrencesResponceModel.Data>) {
        Orderlist.addAll(list)
        notifyDataSetChanged()
    }
    fun clearList() {
        Orderlist.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.articales_categories_adapter,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.text.setText(Orderlist.get(position).prefrence)
    }

    override fun getItemCount(): Int {
        return Orderlist.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    interface CallBack_pref
    {
        fun onItemClick_pref(posi:Int,strPrefrenceIds:String?)
    }

}
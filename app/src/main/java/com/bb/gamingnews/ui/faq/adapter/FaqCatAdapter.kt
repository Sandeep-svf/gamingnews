package com.bb.gamingnews.ui.faq.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.faq.model.GetFaqCategoryResponceModel
import kotlinx.android.synthetic.main.faq_cat_adapter.view.*

class FaqCatAdapter(var requireContext: Context,
                    var callBack:FaqCallback ,
                    var listTexts: List<GetFaqCategoryResponceModel.Data>) : RecyclerView.Adapter<FaqCatAdapter.ViewHolder>() {

   var firstCheck:Boolean=false
    var myposition=0
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FaqCatAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.faq_cat_adapter,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: FaqCatAdapter.ViewHolder, position: Int) {
//        holder.itemView.text.setText(listTexts[position].category)
        holder.setData(position)
    }

    override fun getItemCount(): Int {
        return listTexts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(position: Int) {

            itemView.text.setText(listTexts[position].category)
//            if(firstCheck==false)
//            {
//                callBack.onItemClickFaq(listTexts[0].id)
//                firstCheck=true
//            }
//
// if (myposition==0)
// {
// callBack.onItemClickFaq(listTexts[myposition].id)
// }
            itemView.setOnClickListener {
                myposition=position
                itemView.text.setTextColor(Color.parseColor("#FFC312"))
                callBack.onItemClickFaq(listTexts[position].id)
            }
        }

    }
    interface  FaqCallback{
        fun onItemClickFaq(position :Int)
    }
}


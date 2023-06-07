package com.bb.gamingnews.ui.account.preferences.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.account.preferences.GetPrefrencesResponceModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.preferences_item.view.*

class PreferencesAdapter(var context: Context,var callback:CallBack_pref) : RecyclerView.Adapter<PreferencesAdapter.ViewHolder>() {
    var selectedPosition = 0

//    var check:Boolean=false
    var Orderlist = mutableListOf<GetPrefrencesResponceModel.Data>()
    fun setData (list: List<GetPrefrencesResponceModel.Data>) {
        Orderlist.addAll(list)
        notifyDataSetChanged()
    }
    fun clearList() {
        Orderlist.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.preferences_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setdata(position)
    }

    override fun getItemCount(): Int {
        return Orderlist.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setdata(position: Int) {

            itemView.tv_name.text = Orderlist.get(position).prefrence
            Glide.with(context).load(Orderlist.get(position).imageUrl).placeholder(R.drawable.space_transparent).into(itemView.imgperson)
                if (selectedPosition === position) {
                    itemView.verifyBtn.visibility=View.VISIBLE
                }
                else
                {
                    itemView.verifyBtn.visibility=View.GONE


                }
                itemView.setOnClickListener(View.OnClickListener {
                        selectedPosition!=position
                        selectedPosition = position
                    notifyDataSetChanged()
                    callback.onItemClick_pref(position,Orderlist.get(position).id.toString())


                })


        }
//        fun setdata(result: NotificationResponseModel.Result) {
//            Glide.with(itemView.img).load(result.contentImage).into(itemView.img)
//            itemView.textMovieTitle.text=result.contentTitle
//        }

    }
    interface CallBack_pref
    {
        fun onItemClick_pref(posi:Int,strPrefrenceIds:String?)
    }

}
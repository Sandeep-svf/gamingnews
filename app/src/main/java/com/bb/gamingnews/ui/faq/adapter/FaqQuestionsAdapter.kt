package com.bb.gamingnews.ui.faq.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.faq.model.GetFaqByCategoryResponceModel
import kotlinx.android.synthetic.main.faq_que_adapter.view.*

class FaqQuestionsAdapter(var context:Context,
                          var listTexts: List<GetFaqByCategoryResponceModel.Data>
                          ) : RecyclerView.Adapter<FaqQuestionsAdapter.ViewHolder>() {
    var mExpandedPosition=-1
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FaqQuestionsAdapter.ViewHolder {
        return ViewHolder(
          LayoutInflater.from(parent.context).inflate(
                R.layout.faq_que_adapter,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: FaqQuestionsAdapter.ViewHolder, position: Int) {

        holder.setData(position)


    }

    override fun getItemCount(): Int {
        return listTexts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(position: Int) {
            val isExpanded = position === mExpandedPosition
            itemView.questions.setText(listTexts[position].question)
            itemView.answer.setText(listTexts[position].answer)
            itemView.answer.setVisibility(if (isExpanded) View.VISIBLE else View.GONE)
            itemView.isActivated = isExpanded
            itemView.setOnClickListener {
                mExpandedPosition = if (isExpanded) -1 else position
//            TransitionManager.beginDelayedTransition(recyclerView)
                notifyDataSetChanged()
            }
        }


    }

}
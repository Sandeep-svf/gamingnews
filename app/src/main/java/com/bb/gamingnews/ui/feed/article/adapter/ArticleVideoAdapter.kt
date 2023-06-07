package com.bb.gamingnews.ui.feed.article.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.ui.feed.article.models.GetAllArticlesResponceModel
import com.bb.gamingnews.utils.DateUtil
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view_article_video.view.*

class ArticleVideoAdapter(var context: Context,var callBack:ArticalsCallback,
                          var listTexts: List<GetAllArticlesResponceModel.Data.LstArticle>) : RecyclerView.Adapter<ArticleVideoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view_article_video,
                parent,
                false
            )
        )
    }
    private lateinit var dataList:List<GetAllArticlesResponceModel.Data.LstArticle>
    fun setDta(dataList:List<GetAllArticlesResponceModel.Data.LstArticle>){
        this.dataList = dataList
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return if(this::dataList.isInitialized){
            dataList.size

        }else{
            0

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(position)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun setData(position: Int) {
//            itemView.setOnClickListener {
//                callBack.onItemArticalClicked(position)
//            }
//
//        }


//
//
//
//        }
//
        fun setData(position: Int) {
            itemView.textView51.text = dataList[position].title
            itemView.textView52.text = dataList[position].createdBy
//            itemView.textView24.text = listTexts[position].createdDate
            itemView.textView24.text = DateUtil.getApiDateMonthOnly(dataList[position].createdDate)

            itemView.setOnClickListener {
               callBack.onItemArticalClicked(
                   position,
                   dataList[position].id.toString(),
                   dataList[position].attachmentUrl,
                   dataList[position].articleContent,
                   dataList[position].createdBy,
                   DateUtil.getApiDateFromCalender(dataList[position].createdDate)!!,
                   dataList[position].title,
                   dataList[position].IsLikedByMe,)
            }

            Glide.with(context)
                .load(dataList[position].attachmentUrl)
                .into(itemView.imageView30)

        }

    }


    interface ArticalsCallback {
        fun onItemArticalClicked(favouriteId: Int?,articalId:String,
                                 image:String,
                                 desc:String,
                                 createdBy:String,
                                 date:String,
                                 title:String,
                                isLike:Boolean)

    }

    fun SearchList(keyWord:String):ArrayList<GetAllArticlesResponceModel.Data.LstArticle>{
        val filterList = ArrayList<GetAllArticlesResponceModel.Data.LstArticle>()
        if(this::dataList.isInitialized){
            for(i in dataList){
                var title = i.title
                var discription = i.articleContent
                if(title.toLowerCase().contains(keyWord.toLowerCase()) || discription.toLowerCase().contains(keyWord.toLowerCase())){
                    filterList.add(i)
                }

            }
        }
        return  filterList

    }


}
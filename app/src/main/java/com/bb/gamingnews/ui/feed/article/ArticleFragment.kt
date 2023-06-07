package com.bb.gamingnews.ui.feed.article

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentArticleBinding
import com.bb.gamingnews.ui.feed.article.adapter.ArticalCategory
import com.bb.gamingnews.ui.feed.article.adapter.ArticleVideoAdapter
import com.bb.gamingnews.ui.feed.article.models.GetAllArticlesResponceModel
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleFragment : BaseFragment<FragmentArticleBinding>(),
    ArticleVideoAdapter.ArticalsCallback ,ArticalCategory.Callback{

    private lateinit var articleAdapter: ArticleVideoAdapter
    private  var list1= ArrayList<GetAllArticlesResponceModel.Data.LstArticle>()
    private  var list2=ArrayList<GetAllArticlesResponceModel.Data.LstArticle>()
    private var valuess:String=""
    private  val mArticalVM:ArticalAllVM by viewModel()
    lateinit var listTexts:List<GetAllArticlesResponceModel.Data>
    var listOwn = mutableListOf<GetAllArticlesResponceModel.Data.LstArticle>()
//    private var listTexts = listOf<String>(
//        "Latest Article", "Player Article", "Influence Articles",  "Latest Article", "Player Article", "Influence Articles"
//    )

    override fun mLayoutRes(): Int {
        return R.layout.fragment_article
    }

    override fun onViewReady() {
        mArticalVM.context=requireContext()
        valuess= arguments?.getString("values", "").toString()

        val useid=   PreferenceManager.getInstance(requireContext()).getUserId
        val username=   PreferenceManager.getInstance(requireContext()).getEmail
        mArticalVM.getallArtical(username,useid.toString())
        mBinding.tvSearch.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                fliterByKeyword(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        observer()

        articleAdapter = ArticleVideoAdapter(requireContext(),this,list1)



    }

    override fun onStart() {
        super.onStart()
        val useid=   PreferenceManager.getInstance(requireContext()).getUserId
        val username=   PreferenceManager.getInstance(requireContext()).getEmail
        mArticalVM.getallArtical(username,useid.toString())

    }

    fun fliterByKeyword(keyWord:String){

       var list2 =  articleAdapter.SearchList(keyWord)
        if(keyWord.length>0){
            if(list2.isNotEmpty()){
                articleAdapter.setDta(list2)
                // articleAdapter=ArticleVideoAdapter(requireContext(),this,list2)

            }else{
                articleAdapter.setDta(list1)

                //  articleAdapter=ArticleVideoAdapter(requireContext(),this,list2)

            }
        }else{
            articleAdapter.setDta(list1)

        }


    }



    private fun observer() {
//...................................All artical .................................................

        mArticalVM.progressIndicator.observe(this, Observer {
            //            toggleLoader(requireContext(), it)
        })
        mArticalVM.mArticalAllVMResponse.observe(this,
            {


                listTexts=it.peekContent().data!!
                val list= it.peekContent().data
                mBinding.categoryrecyler.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                mBinding.categoryrecyler.adapter= ArticalCategory(requireContext(),this,list!!)


            })

        mArticalVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView29, it)
        })


    }


    override fun onItemClicked(favouriteId: Int?, category: String?) {
        list1 = ArrayList()
         list1.addAll(listTexts[favouriteId!!].lstArticles)
//        val listOwn:List<GetAllArticlesResponceModel.Data.LstArticle>
        Log.v("qwertyu","lp: "+valuess)
        if(valuess.equals("dashboaed"))
        {
            mBinding.videorecycler.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            articleAdapter.setDta(list1)
            mBinding.videorecycler.adapter = articleAdapter
        }
        else
        {
            for (obj in listTexts[favouriteId!!].lstArticles) {
                if (obj.isPostedByMe) {
                    listOwn.add(obj)
//                    Log.v("qwertyu",""+listOwn.size)
                } else {
//                    Log.v("qwertyu","op"+listOwn.size.toByte())
                }
            }
            mBinding.videorecycler.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            mBinding.videorecycler.adapter=ArticleVideoAdapter(requireContext(),this,listOwn)
        }

        Log.v("2adsfsdfssdsf",list1.size.toString())


    }

    override fun onItemArticalClicked(
        favouriteId: Int?,
        articalId: String,
        image: String,
        desc: String,
        createdBy: String,
        date: String,
        title: String,
        IsLike: Boolean
    ) {
        val args = Bundle()
        args.putString("Image",image)
        args.putString("articalId",articalId)
        args.putString("Headline",title)
        args.putString("Creted",createdBy)
        args.putString("date", date)
        args.putString("desc",desc)
        args.putString("Islike",IsLike.toString())
        findNavController().navigate(R.id.articleDetailsFragment,args)
    }




}
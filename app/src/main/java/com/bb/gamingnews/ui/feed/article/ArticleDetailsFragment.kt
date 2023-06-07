package com.bb.gamingnews.ui.feed.article

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentArticleDetailsBinding
import com.bb.gamingnews.extentions.doBack
import com.bb.gamingnews.ui.dashboard.DashboardVM
import com.bb.gamingnews.utils.DateUtil
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import com.bb.gamingnews.utils.RemoveHtmlTags
import com.bb.gamingnews.utils.share.ShareUrl
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel


class ArticleDetailsFragment : BaseFragment<FragmentArticleDetailsBinding>() {
    private  var heading:String=""
    private  var articalId:String=""
    var Islike:String=""
    private val mDashBoardViewModel: DashboardVM by viewModel()
    var articalIds :String=""
     var useid :String=""
    private  val mArticalVM:ArticalAllVM by viewModel()

    override fun mLayoutRes(): Int {

        return R.layout.fragment_article_details
    }

    override fun onViewReady() {
        mBinding.imageView41.doBack()
        mDashBoardViewModel.context=requireContext()
        mArticalVM.context=requireContext()
         useid=   PreferenceManager.getInstance(requireContext()).getUserId.toString()
        val username=   PreferenceManager.getInstance(requireContext()).getEmail


       mBinding.imageshare.setOnClickListener {
           mDashBoardViewModel.type="Article"
           mDashBoardViewModel.Id=articalId
           mDashBoardViewModel.Title=heading
           ShareUrl.deeplinkingUrl(requireContext(),heading,mBinding.imageView,mDashBoardViewModel.shortLink.value!!)

       }

        mBinding.imgcomment.setOnClickListener {
            val args = Bundle()
            args.putString("heading",heading)
            args.putString("articalId",articalId)
            findNavController().navigate(R.id.commentFragmant,args)
        }
        if(arguments!=null)
        {
            val type = requireArguments().getString("type", "")
//            val Image = requireArguments().getString("Image", "")
//            val Headline = requireArguments().getString("Headline", "")
             articalIds = requireArguments().getString("articalId", "")
//            heading=Headline
            articalId=articalIds
//            val Creted = requireArguments().getString("Creted", "")
//            val date = requireArguments().getString("date", "")
//            val desc = requireArguments().getString("desc", "")
//             Islike = requireArguments().getString("Islike", "")
//            Glide.with(requireContext()).load(Image).placeholder(R.drawable.placeholder).into(mBinding.imageView)
//            mBinding.textView39.setText(Headline)
//            Log.v("sgygcdsg",">>>>>>>>>>"+RemoveHtmlTags.stripHtml(desc))
//            mBinding.textView41.setText(RemoveHtmlTags.stripHtml(desc))
//            mBinding.articalMember.setText(" "+Creted)
//            mBinding.txtdd.setText(date)

            mArticalVM.GetArticles(type,articalId)


            if(!(Islike.isEmpty()))
            {
                if(Islike.equals("false"))
                {
                    mBinding.imgLike.setImageResource(R.drawable.blank_heart)
                }
                else
                {
                    mBinding.imgLike.setImageResource(R.drawable.fill_heart)
                }
            }
            mBinding.imgLike.setOnClickListener {
//            mBinding.imgLike.setImageResource(R.drawable.fill_heart)

                if(!(Islike.isEmpty()))
                {
                    if(Islike.equals("false"))
                    {
                        mArticalVM.likeArticle(username,articalIds,useid,"1")
                    }

                }


            }




        }

//        mBinding.articalMember.setOnClickListener {
//            findNavController().navigate(R.id.teamMemberProfileFragment)
//        }
        observer()
    }

    private fun observer() {
//...................................Like artical .................................................

        mArticalVM.progressIndicator.observe(this,  {
            //            toggleLoader(requireContext(), it)
        })
        mArticalVM.mArticalLikeVMResponse.observe(this,
            {
                if(it.peekContent().data==true)
                {
                    mBinding.imgLike.setImageResource(R.drawable.fill_heart)

                }

            })

        mArticalVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView, it)
        })


        //...................................Get artical .................................................

        mArticalVM.progressIndicator.observe(this,  {
            //            toggleLoader(requireContext(), it)
        })
        mArticalVM.mGetArticlesResponse.observe(this,
            {
                if(it.peekContent().data!!.isNotEmpty())
                {
                    Glide.with(requireContext()).load((it.peekContent().data?.get(0)!!.attachmentUrl)).placeholder(R.drawable.placeholder).into(mBinding.imageView)
                    mBinding.textView39.setText(it.peekContent().data?.get(0)!!.title)
//                    Log.v("sgygcdsg",">>>>>>>>>>"+RemoveHtmlTags.stripHtml(desc))
                    mBinding.textView41.setText(RemoveHtmlTags.stripHtml(it.peekContent().data?.get(0)!!.articleContent))
                    mBinding.articalMember.setText(" "+it.peekContent().data?.get(0)!!.createdBy)
                    mBinding.txtdd.setText(DateUtil.getApiDateFromCalender(it.peekContent().data?.get(0)!!.createdDate))
                }

            })

        mArticalVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView, it)
        })


    }


}
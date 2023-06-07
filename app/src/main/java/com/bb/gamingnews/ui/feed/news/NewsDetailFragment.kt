package com.bb.gamingnews.ui.feed.news

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentNewsDetailBinding
import com.bb.gamingnews.extentions.doBack
import com.bb.gamingnews.extentions.showToast
import com.bb.gamingnews.ui.allnews.NewsAllVM
import com.bb.gamingnews.ui.dashboard.DashboardVM
import com.bb.gamingnews.ui.feed.adapter.FeedCategoryAdapter
import com.bb.gamingnews.ui.feed.interview.InterviewAllVM
import com.bb.gamingnews.utils.DateUtil
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import com.bb.gamingnews.utils.RemoveHtmlTags
import com.bb.gamingnews.utils.share.ShareUrl
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.String
import kotlin.Int


class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>() {

    var Type=""
    var Username=""
    var LoginId=""

    var Id="0"
    private val mDashBoardViewModel: DashboardVM by viewModel()
    private val mInterviewVm: InterviewAllVM by viewModel()
    private val mNewsDetailsVm: NewsAllVM by viewModel()


    override fun mLayoutRes(): Int {
        return R.layout.fragment_news_detail
    }

    override fun onViewReady() {
        mBinding.imageView42.doBack()
        mDashBoardViewModel.context=requireContext()
        mNewsDetailsVm.context=requireContext()
        mInterviewVm.context=requireContext()

        val builder: StrictMode.VmPolicy.Builder =StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())


        Username=PreferenceManager.getInstance(requireContext()).getUserId.toString()
        LoginId=PreferenceManager.getInstance(requireContext()).getEmail
        mBinding.imageView26.setOnClickListener {
//            ShareUrl()
            mDashBoardViewModel.type=Type
            mDashBoardViewModel.Id=Id
            mDashBoardViewModel.onCreateLinkClick(mBinding.imageView)
//            val linkhs=mDashBoardViewModel.shortLink.value!!
//            Log.v("nsjbsdfjd",">>>>"+linkhs+">>>>"+Type+">>>>"+Id)
    //            ShareUrl.deeplinkingUrl(requireContext(),mBinding.imageView,mDashBoardViewModel.shortLink.value!!)
//            com.bb.gamingnews.utils.share.ShareUrl.deeplinkingUrl(requireContext(), mBinding.imageView,Type,Id)

        }


        if (arguments!=null)
        {
//            val Image = requireArguments()!!.getString("Image", "")
            Type = requireArguments()!!.getString("type", "")
            Id = requireArguments()!!.getString("id", "")

            if(Type.equals("News"))
            {
                mNewsDetailsVm.Getnews(Type,Id,Username,LoginId)
            }
            else if(Type.equals("Interview"))
            {
                mInterviewVm.GetInterview(Type,Id,Username,LoginId)

            }
//            Log.v("ffhdjff",">>>>>"+Type)


//            val Headline = requireArguments()!!.getString("Headline", "")
//            val Creted = requireArguments()!!.getString("Creted", "")
//            val date = requireArguments()!!.getString("date", "")
//            val desc = requireArguments()!!.getString("desc", "")
//
//            Glide.with(requireContext()).load(Image).placeholder(R.drawable.placeholder).into(mBinding.imageView)
//            mBinding.textView39.setText(Headline)
//            mBinding.textView41.setText(RemoveHtmlTags.stripHtml(desc))
//            mBinding.txtadate.setText(date)
//            mBinding.txtCreted.setText(Creted)
        }
        observer()
    }


    private fun observer() {


        //.................Get News Details.......................................................
        mNewsDetailsVm.progressIndicator.observe(this,  {
            //            toggleLoader(requireContext(), it)
        })
        mNewsDetailsVm.mNewsDetailsResponse.observe(this,
            {

                if(it.peekContent().data!!.isNotEmpty())
                {
                    Log.v("ffhdjff",">>>>>"+ it.peekContent().data!![0])
                    Glide.with(requireContext()).load(it.peekContent().data?.get(0)!!.imageUrl).placeholder(R.drawable.placeholder).into(mBinding.imageView)
                    mBinding.textView39.setText(it.peekContent().data?.get(0)!!.headline)
                    mBinding.textView41.setText(RemoveHtmlTags.stripHtml(it.peekContent().data?.get(0)!!.newsDescription))
                    if(it.peekContent().data?.get(0)?.createdDate!!.isNotEmpty())
                    {
                        mBinding.txtadate.setText(DateUtil.getApiDateFromCalender(it.peekContent().data?.get(0)!!.createdDate))

                    }
                    mBinding.txtCreted.setText(it.peekContent().data?.get(0)!!.createdBy)
                }
                else
                {
                    showToast("Data not found !")
                }

            })

        mNewsDetailsVm.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView, it)
        })
//...............................................................................................


//.................Get Interview Details.......................................................
        mInterviewVm.progressIndicator.observe(this,  {
            //            toggleLoader(requireContext(), it)
        })
        mInterviewVm.mGetInterviewModelResponse.observe(this,
            {

                if(it.peekContent().data!!.isNotEmpty())
                {

                    Glide.with(requireContext()).load(it.peekContent().data?.get(0)!!.imageUrl).placeholder(R.drawable.placeholder).into(mBinding.imageView)
                    mBinding.textView39.setText(it.peekContent().data?.get(0)!!.title)
                    mBinding.textView41.setText(RemoveHtmlTags.stripHtml(it.peekContent().data?.get(0)!!.description))
                    if(it.peekContent().data?.get(0)?.createdDate!!.isNotEmpty())
                    {
                        mBinding.txtadate.setText(DateUtil.getApiDateFromCalender(it.peekContent().data?.get(0)!!.createdDate))

                    }
                    mBinding.txtCreted.setText(it.peekContent().data?.get(0)!!.createdBy)
                }
                else
                {
                    showToast("Data not found !")
                }

            })

        mInterviewVm.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView, it)
        })
//...............................................................................................

    }

}
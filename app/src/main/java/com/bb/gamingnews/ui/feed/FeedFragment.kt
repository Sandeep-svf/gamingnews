package com.bb.gamingnews.ui.feed

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentFeedBinding
import com.bb.gamingnews.ui.allnews.NewsAllVM
import com.bb.gamingnews.ui.allnews.model.NewsResponceModels
import com.bb.gamingnews.ui.dashboard.DashboardVM
import com.bb.gamingnews.ui.feed.adapter.FeedAapter
import com.bb.gamingnews.ui.feed.adapter.FeedCategoryAdapter
import com.bb.gamingnews.utils.DateUtil
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import com.bb.gamingnews.utils.RemoveHtmlTags
import com.bb.gamingnews.utils.share.ShareUrl
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view_feed.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment : BaseFragment<FragmentFeedBinding>(),FeedCategoryAdapter.Callback,FeedAapter.Callback {

    private val mNewsVm: NewsAllVM by viewModel()
    lateinit var listTexts:List<NewsResponceModels.Data>
    private val mDashBoardViewModel: DashboardVM by viewModel()

    var id="0"
    var type="news"
//    private var listTexts = listOf<String>(
//        "Latest Poker News", "Latest Rummy  News ",  "Latest Poker News", "Latest Rummy  News "
//    )
    override fun mLayoutRes(): Int {
        return R.layout.fragment_feed
    }

    override fun onViewReady() {

        mNewsVm.context=requireContext()
        mDashBoardViewModel.context=requireContext()

        var email= PreferenceManager.getInstance(requireContext()).getEmail
        var userid= PreferenceManager.getInstance(requireContext()).getUserId
        var rollid= PreferenceManager.getInstance(requireContext()).getRoleId
        mNewsVm.allnews(email,rollid.toString(),userid.toString())

        mBinding.imageView20.setOnClickListener {
            mDashBoardViewModel.type=type
            mDashBoardViewModel.Id=id
            mDashBoardViewModel.onCreateLinkClick(mBinding.img1)
//            mDashBoardViewModel.image=
//            ShareUrl.deeplinkingUrl(requireContext(),mBinding.img1, mDashBoardViewModel.shortLink.value!!)
        }


//        mBinding.newsContent.setOnClickListener {
//
//            findNavController().navigate(R.id.newsDetailFragment)

//        }


        observer()
    }
    private fun observer() {
//...................................news .................................................

        mNewsVm.progressIndicator.observe(this, Observer {
            //            toggleLoader(requireContext(), it)
        })
        mNewsVm.mAllNewsResponse.observe(this,
            {

                listTexts=it.peekContent().data!!
//                val list= it.peekContent().data
                mBinding.categoryfeed.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                mBinding.categoryfeed.adapter = FeedCategoryAdapter(requireContext(),this,listTexts)

            })

        mNewsVm.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageCardView, it)
        })


    }

    override fun onItemClickedNews(favouriteId: Int?, category: String?) {
        val list1= listTexts[favouriteId!!].lstNews

        Log.v("2adsfsdfssdsf",list1!!.size.toString())
        id=list1[0].id.toString()
        mBinding.textView36.setText(list1[0].headline)
        mBinding.newsContent.setText(RemoveHtmlTags.stripHtml(list1[0].newsDescription))
        mBinding.txtCredte.setText(list1[0].createdBy)
        mBinding.txtDatesss.setText(DateUtil.getApiDateFromCalender(list1[0].createdDate))
        mBinding.imageCardView.setOnClickListener {


            val args = Bundle()
            args.putString("Image",list1[0].imageUrl)
            args.putString("type","News")
            args.putString("id",list1[0].id.toString())
            args.putString("Headline",list1[0].headline)
            args.putString("Creted",list1[0].createdBy)
            args.putString("date",DateUtil.getApiDateFromCalender(list1[0].createdDate))
            args.putString("desc",list1[0].newsDescription)
            findNavController().navigate(R.id.newsDetailFragment,args)


        }
        mBinding.imgDetails.setOnClickListener {
            val args = Bundle()
            args.putString("type","News")
            args.putString("id",list1[0].id.toString())
            args.putString("Image",list1[0].imageUrl)
            args.putString("Headline",list1[0].headline)
            args.putString("Creted",list1[0].createdBy)
            args.putString("date",DateUtil.getApiDateFromCalender(list1[0].createdDate))
            args.putString("desc",list1[0].newsDescription)
            findNavController().navigate(R.id.newsDetailFragment,args)

        }
        Glide.with(requireContext())
            .load(list1[0].imageUrl).placeholder(R.drawable.placeholder)
            .into(mBinding.img1)
        mBinding.feedrecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        mBinding.feedrecycler.adapter = FeedAapter(requireContext(),this,list1!!)

    }

    override fun onNewsItemClicked(favouriteId: Int?) {
    }
}
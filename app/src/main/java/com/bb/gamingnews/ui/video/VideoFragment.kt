package com.bb.gamingnews.ui.video

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentVideoBinding
import com.bb.gamingnews.ui.allvideo.AllVideoVM
import com.bb.gamingnews.ui.allvideo.models.AllVideoResponceResult
import com.bb.gamingnews.ui.video.adapter.RecentVideoAdapter
import com.bb.gamingnews.ui.video.adapter.VideoCategoryAdapter
import com.bb.gamingnews.ui.video.adapter.VideoLiveAdapter
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import org.koin.androidx.viewmodel.ext.android.viewModel


class VideoFragment : BaseFragment<FragmentVideoBinding>(),
    RecentVideoAdapter.Callback,VideoLiveAdapter.Callback,VideoCategoryAdapter.Callback {

    private val mAllvideoModel: AllVideoVM by viewModel()
//    var listTexts= mutableListOf<AllVideoResponceResult.Data>()
lateinit var listTexts:List<AllVideoResponceResult.Data>
    override fun mLayoutRes(): Int {
        return R.layout.fragment_video
    }

    override fun onViewReady() {
        mAllvideoModel.context=requireContext()
        val useid=   PreferenceManager.getInstance(requireContext()).getUserId
        val username=   PreferenceManager.getInstance(requireContext()).getEmail
        mAllvideoModel.allVideo(username,"0",useid.toString())
//        mAllvideoModel.allVideo("kshitij.mudgal@bizbrolly.com","0","1")







        observer()

    }
    private fun observer() {
//...................................All videos .................................................

        mAllvideoModel.progressIndicator.observe(this, Observer {
            //            toggleLoader(requireContext(), it)
        })
        mAllvideoModel.mAllVideoResponse.observe(this,
            {

                listTexts=it.peekContent().data!!
                val list= it.peekContent().data
//                val list1= it.peekContent().data?.get(1)?.lstNews

                mBinding.recyclerViewCategory.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                mBinding.recyclerViewCategory.adapter=VideoCategoryAdapter(requireContext(),this,list!!)




//                mBinding.interviewrecycler.layoutManager= LinearLayoutManager(requireContext())
//                mBinding.interviewrecycler.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
//                mBinding.recyclerView.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
//
//
//                mBinding.interviewrecycler.adapter= NewsAllAdapter(requireContext(),this,list!!)
//                mBinding.recyclerView.adapter= NewsAdapteAllrHorizontal(requireContext(),this,list!!)
            })

        mAllvideoModel.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.recyclerViewCategory, it)
        })


    }




    override fun onItemClickedCategoryName(favouriteId: Int?, category: String?) {

        val list1= listTexts[favouriteId!!].lstVideos
        Log.v("2adsfsdfssdsf",list1.toString())

            mBinding.recyclerViewLivevideo.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            mBinding.recyclerViewLivevideo.adapter=VideoLiveAdapter(requireContext(),this,list1!!)

        mBinding.recentlyvieoRecycler.layoutManager=LinearLayoutManager(requireContext())
        mBinding.recentlyvieoRecycler.adapter=RecentVideoAdapter(requireContext(),this,list1!!)
    }



    override fun videoLiveonItemClicked(
        favouriteId: Int?,
        title: String?,
        createby: String?,
        views: String,
        like: String,
        desc: String?,
        ThumbnailImageUrl: String?,
        VideoUrl: String?,
        VideoLength: String?
    ) {
        Log.v("dhjdbhbdsbhd","VideoId >>>"+favouriteId)
        val args = Bundle()
        args.putString("Title",title)
        args.putString("type","Video")
            args.putString("videoId",favouriteId.toString())
        args.putString("createby",createby)
        args.putString("views",views)
        args.putString("like",like)
        args.putString("desc",desc)
        args.putString("ThumbnailImageUrl",ThumbnailImageUrl)
        args.putString("VideoUrl",VideoUrl)
        args.putString("VideoLength",VideoLength)
//        fragment.setArguments(args)
//        findNavController().navigate(R.id.videoDetailFragment)
        findNavController().navigate(R.id.videoDetailFragment,args)
    }

    override fun onItemClickedRecentVideo(
        favouriteId: Int?,
        title: String?,
        createby: String?,
        views: String,
        like: String,
        desc: String?,
        ThumbnailImageUrl: String?,
        VideoUrl: String?,
        VideoLength: String?
    ) {
        val args = Bundle()
        args.putString("Title",title)
        args.putString("type","Video")
        args.putString("videoId",favouriteId.toString())
        args.putString("createby",createby)
        args.putString("views",views)
        args.putString("like",like)
        args.putString("desc",desc)
        args.putString("ThumbnailImageUrl",ThumbnailImageUrl)
        args.putString("VideoUrl",VideoUrl)
        args.putString("VideoLength",VideoLength)
//        fragment.setArguments(args)
//        findNavController().navigate(R.id.videoDetailFragment)
        findNavController().navigate(R.id.videoDetailFragment,args)
    }


}
package com.bb.gamingnews.ui.allvideo

import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.AllvideoFragmentBinding
import com.bb.gamingnews.ui.dashboard.adapter.*
import com.bb.gamingnews.utils.PreferenceManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllVideoFragment : BaseFragment<AllvideoFragmentBinding>() {
    private val mAllvideoModel: AllVideoVM by viewModel()


    override fun mLayoutRes(): Int {
        return R.layout.allvideo_fragment
    }

    override fun onViewReady() {
        mAllvideoModel.context=requireContext()
       val roleId= PreferenceManager.getInstance(requireContext()).getRoleId
       val userId= PreferenceManager.getInstance(requireContext()).getUserId
       val username= PreferenceManager.getInstance(requireContext()).getEmail
        mAllvideoModel.allVideo(username,roleId.toString(),userId.toString())

    }
//    private fun observer() {
//
//        mAllvideoModel.progressIndicator.observe(this,  {
////            toggleLoader(requireContext(), it)
//        })
//        mAllvideoModel.mAllVideoResponse.observe(this,
//            {
//
//
//                //.................................................video.........................................
//                mBinding.recyclerviewAllVideo.layoutManager = LinearLayoutManager(requireContext(),
//                    LinearLayoutManager.VERTICAL,false)
////                mBinding.recyclerviewAllVideo.adapter = VideoAdapter(requireContext(),this,it.peekContent().data?.lstVideos!!)
//
//            })
//
//        mAllvideoModel.errorResponse.observe(this, {
//            ErrorUtil.handlerGeneralError(requireContext(), mBinding.cardView3, it)
//        })
////...............................GetAdds.....................................................
//
//
//
//
//    }
}
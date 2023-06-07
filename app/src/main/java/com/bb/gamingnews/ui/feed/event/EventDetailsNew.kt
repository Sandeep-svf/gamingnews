package com.bb.gamingnews.ui.feed.event

import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.EventDetailsNewBinding
import com.bb.gamingnews.ui.dashboard.DashBoardShareVm
import com.bb.gamingnews.ui.dashboard.Models.DashboardResponceModel
import com.bb.gamingnews.ui.dashboard.adapter.OnGoingAdapter
import com.bb.gamingnews.ui.dashboard.adapter.SubEventVideoInnerDashboardAdapter
import com.bb.gamingnews.ui.feed.event.adapter.EventDetailsNewAdapter
import com.bb.gamingnews.ui.feed.event.adapter.EventDetailsNewAllAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EventDetailsNew :BaseFragment<EventDetailsNewBinding>(),
    OnGoingAdapter.Callback,
    SubEventVideoInnerDashboardAdapter.Callbacks {
    private val mDashBoardShareVm: DashBoardShareVm by sharedViewModel()

    override fun mLayoutRes(): Int {
       return R.layout.event_details_new
    }

    override fun onViewReady() {
//        mBinding.rveventDetails.adapter= EventSubCategoryAdapter(requireContext(),this, listSecond)
        mDashBoardShareVm.list.observe(this,{
//            showToast("lllllllllllll"+it.toString(),requireContext())
            mBinding.rveventDetails.adapter= EventDetailsNewAdapter(requireContext(), it)


        })

mDashBoardShareVm.listEventList.observe(this,{
//            showToast("lllllllllllll"+it.toString(),requireContext())
            mBinding.rveventDetails.adapter= EventDetailsNewAllAdapter(requireContext(), it)


        })


    }

    override fun onItemInnerClicked(listTexts: List<DashboardResponceModel.Data.LstEvent.LstSubEvent.LstReporting>) {

     //  var list = arguments?.getSerializable("bankDetails")
//        Log.e("list","list->"+mDashBoardShareVm.list.toString())
    }

    override fun subeventonItemClicked(listTexts: List<DashboardResponceModel.Data.LstEvent.LstSubEvent.LstReporting>) {
//       showToast("lllllllllllll"+listTexts.size.toString(),requireContext())
        mBinding.rveventDetails.adapter= EventDetailsNewAdapter(requireContext(), listTexts)

    }
}
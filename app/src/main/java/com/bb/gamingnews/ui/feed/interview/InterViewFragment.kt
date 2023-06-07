package com.bb.gamingnews.ui.feed.interview

import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentInterViewBinding
import com.bb.gamingnews.ui.feed.interview.adapter.InterviewAllAdapter
import com.bb.gamingnews.ui.feed.interview.model.GetAllInterviewResponceModel
import com.bb.gamingnews.utils.DateUtil
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import org.koin.androidx.viewmodel.ext.android.viewModel


class InterViewFragment : BaseFragment<FragmentInterViewBinding>(),
    InterviewAllAdapter.Callback,NewsAdapterHorizontal.Callback {


    private val mInterviewAllVM: InterviewAllVM by viewModel()
    lateinit var listInterviewTexts:List<GetAllInterviewResponceModel.Data>
    override fun mLayoutRes(): Int {
        return R.layout.fragment_inter_view
    }

    override fun onViewReady() {

        mInterviewAllVM.context=requireContext()
        mInterviewAllVM.getallInterviews(PreferenceManager.getInstance(requireContext()).getEmail,
        PreferenceManager.getInstance(requireContext()).getUserId.toString())
        observer()
    }

    override fun onItemClicked(favouriteId: Int?) {
        val args = Bundle()
        args.putString("Image",listInterviewTexts[favouriteId!!].imageUrl)
        args.putString("type","Interview")
        args.putString("id",listInterviewTexts[favouriteId!!].id.toString())
        args.putString("Headline",listInterviewTexts[favouriteId!!].title)
        args.putString("Creted",listInterviewTexts[favouriteId!!].createdBy)
        args.putString("date", DateUtil.getApiDateFromCalender(listInterviewTexts[favouriteId!!].createdDate))
        args.putString("desc",listInterviewTexts[favouriteId!!].interviewContent)
        findNavController().navigate(R.id.newsDetailFragment,args)
    }

    override fun NewsonItemClicked(favouriteId: Int?) {
        val args = Bundle()
        args.putString("type","Interview")
        args.putString("Image",listInterviewTexts[favouriteId!!].imageUrl)
        args.putString("id",listInterviewTexts[favouriteId!!].id.toString())
        args.putString("Headline",listInterviewTexts[favouriteId!!].title)
        args.putString("Creted",listInterviewTexts[favouriteId!!].createdBy)
        args.putString("date", DateUtil.getApiDateFromCalender(listInterviewTexts[favouriteId!!].createdDate))
        args.putString("desc",listInterviewTexts[favouriteId!!].interviewContent)
        findNavController().navigate(R.id.newsDetailFragment,args)
        Log.v("gdggdggdggdgd",listInterviewTexts[favouriteId!!].imageUrl)
//       findNavController().navigate(R.id.newsDetailFragment)
    }
    private fun observer() {
        mInterviewAllVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mInterviewAllVM.mInterviewAllVMResponse.observe(this,
            {
                listInterviewTexts=it.peekContent().data
                mBinding.interviewrecycler.layoutManager=LinearLayoutManager(requireContext())
                mBinding.recyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                mBinding.interviewrecycler.adapter= InterviewAllAdapter(requireContext(),this,it.peekContent().data)
                mBinding.recyclerView.adapter=NewsAdapterHorizontal(requireContext(),this,it.peekContent().data)

            })
        mInterviewAllVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.constraintLayout12, it)
        })
    }
}
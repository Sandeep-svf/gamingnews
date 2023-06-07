package com.bb.gamingnews.ui.gametips

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentGametipsBinding
import com.bb.gamingnews.ui.gametips.adapter.GameCategoryAdapter
import com.bb.gamingnews.ui.gametips.adapter.GameTipsAdapter
import com.bb.gamingnews.ui.gametips.model.GametipCategoryResponceModel
import com.bb.gamingnews.ui.gametips.model.GetGameTipsResponceModel
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import org.koin.androidx.viewmodel.ext.android.viewModel


class GameTipFragment : BaseFragment<FragmentGametipsBinding>(),
    GameCategoryAdapter.Callback {

    private val mAllgametipModel: AllGametipsVM by viewModel()
//    var listTexts= mutableListOf<AllVideoResponceResult.Data>()
lateinit var listTextsCategory:List<GametipCategoryResponceModel.Data>
lateinit var listGameTips:List<GetGameTipsResponceModel.Data>
    override fun mLayoutRes(): Int {
        return R.layout.fragment_gametips
    }

    override fun onViewReady() {

        mAllgametipModel.context=requireContext()
        val useid=   PreferenceManager.getInstance(requireContext()).getUserId
        val username=   PreferenceManager.getInstance(requireContext()).getEmail
        mAllgametipModel.getallGetGameTipsTitles(username,useid.toString())
        observer()

    }
    private fun observer() {
//...................................All gametip category .................................................

        mAllgametipModel.progressIndicator.observe(this, Observer {
            //            toggleLoader(requireContext(), it)
        })
        mAllgametipModel.mAllGametipResponse.observe(this,
            {

                listTextsCategory=it.peekContent().data!!
//                val list= it.peekContent().data
                mBinding.recyclerViewCategory.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                mBinding.recyclerViewCategory.adapter= GameCategoryAdapter(requireContext(),this,listTextsCategory!!)


            })
        mAllgametipModel.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.recyclerViewCategory, it)
        })

     //................................gametips list................................
        mAllgametipModel.progressIndicator.observe(this, Observer {
            //            toggleLoader(requireContext(), it)
        })
        mAllgametipModel.mGametipResponse.observe(this,
            {

                listGameTips=it.peekContent().data!!
//                val list= it.peekContent().data
//                mBinding.recyclerviewgametip.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                mBinding.recyclerviewgametip.adapter= GameTipsAdapter(requireContext(),listGameTips!!)


            })
        mAllgametipModel.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.recyclerViewCategory, it)
        })


    }


    override fun onItemClickedCategoryName(favouriteId: Int?, category: String?) {
//        showToast("value"+category,requireContext())
        val useid=   PreferenceManager.getInstance(requireContext()).getUserId
        val username=   PreferenceManager.getInstance(requireContext()).getEmail
        mAllgametipModel.getGetGameTips(username,category!!,useid.toString())

//            mBinding.recyclerViewLivevideo.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
//            mBinding.recyclerViewLivevideo.adapter=VideoLiveAdapter(requireContext(),this,list1!!)

//        mBinding.recentlyvieoRecycler.layoutManager=LinearLayoutManager(requireContext())
//        mBinding.recentlyvieoRecycler.adapter=RecentVideoAdapter(requireContext(),this,list1!!)

    }



}
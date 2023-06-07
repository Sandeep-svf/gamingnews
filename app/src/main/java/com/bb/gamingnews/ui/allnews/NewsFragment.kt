package com.bb.gamingnews.ui.allnews

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.NewsFragmentBinding
import com.bb.gamingnews.ui.allnews.adapter.NewsAdapteAllrHorizontal
import com.bb.gamingnews.ui.allnews.adapter.NewsAllAdapter
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment :BaseFragment<NewsFragmentBinding>(),
    NewsAllAdapter.Callback, NewsAdapteAllrHorizontal.Callback {

    private val mNewsVm:NewsAllVM by viewModel()
    override fun mLayoutRes(): Int {


        return R.layout.news_fragment
    }

    override fun onViewReady() {
        mNewsVm.context=requireContext()
        val email= PreferenceManager.getInstance(requireContext()).getEmail
        val userid= PreferenceManager.getInstance(requireContext()).getUserId
        val rollid= PreferenceManager.getInstance(requireContext()).getRoleId
        mNewsVm.allnews(email,rollid.toString(),userid.toString())
        observer()
    }
 private fun observer() {
//...................................news .................................................

        mNewsVm.progressIndicator.observe(this, Observer {
            //            toggleLoader(requireContext(), it)
        })
        mNewsVm.mAllNewsResponse.observe(this,
            {

                val list= it.peekContent().data?.get(0)?.lstNews
                val list1= it.peekContent().data?.get(1)?.lstNews
                mBinding.interviewrecycler.layoutManager= LinearLayoutManager(requireContext())
                mBinding.interviewrecycler.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
                mBinding.recyclerView.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)


                mBinding.interviewrecycler.adapter= NewsAllAdapter(requireContext(),this,list!!)
                mBinding.recyclerView.adapter= NewsAdapteAllrHorizontal(requireContext(),this,list!!)


            })

        mNewsVm.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.recyclerView, it)
        })


    }

    override fun onItemClicked(favouriteId: Int?) {

    }

    override fun NewsonItemClicked(favouriteId: Int?) {


    }
}
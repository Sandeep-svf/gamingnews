package com.bb.gamingnews.ui.faq

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FaqFragmentBinding
import com.bb.gamingnews.ui.faq.adapter.FaqCatAdapter
import com.bb.gamingnews.ui.faq.adapter.FaqQuestionsAdapter
import com.bb.gamingnews.ui.faq.model.GetFaqCategoryResponceModel
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class FaqFragment : BaseFragment<FaqFragmentBinding>(),FaqCatAdapter.FaqCallback{

    private val mGetcaytegoryVm:FaqVM by viewModel()
    lateinit  var listTexts:List<GetFaqCategoryResponceModel.Data>
//    private var listTexts = listOf<String>(
//        "Player Interview", "Article", "Feed",  "Videos", "Player Article", "Influence Articles"
//    )
    override fun mLayoutRes(): Int {
       return R.layout.faq_fragment
    }

    override fun onViewReady() {

        mGetcaytegoryVm.context=requireContext()
        val username=PreferenceManager.getInstance(requireContext()).getEmail
        val userid=PreferenceManager.getInstance(requireContext()).getUserId

        mGetcaytegoryVm.getallCategory(username,userid.toString())
        observer()
    }

    private fun observer() {
//...................................All category .................................................

        mGetcaytegoryVm.progressIndicator.observe(this, Observer {
            //            toggleLoader(requireContext(), it)
        })
        mGetcaytegoryVm.mFaqVMResponse.observe(this,
            {

                listTexts=it.peekContent().data!!
//                val list= it.peekContent().data
                mBinding.categoryRecycler.adapter=FaqCatAdapter(requireContext(),this,listTexts)

//                mBinding.categoryrecyler.layoutManager=
//                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
//                mBinding.categoryrecyler.adapter= ArticalCategory(requireContext(),this,list!!)


            })

        mGetcaytegoryVm.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView29, it)
        })

        //........................All faq by category................................

        mGetcaytegoryVm.progressIndicator.observe(this, Observer {
            //            toggleLoader(requireContext(), it)
        })
        mGetcaytegoryVm.mFaqByCategoryVMResponse.observe(this,
            {

//                listTexts=it.peekContent().data!!
                val list= it.peekContent().data
//                mBinding.categoryRecycler.adapter=FaqCatAdapter(requireContext(),this,listTexts)

                mBinding.faqQueRecycler.addItemDecoration(DividerItemDecoration(requireContext(),RecyclerView.VERTICAL))
                mBinding.faqQueRecycler.adapter=FaqQuestionsAdapter(requireContext(),list)


            })

        mGetcaytegoryVm.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView29, it)
        })


    }

    override fun onItemClickFaq(position: Int) {
        Log.v("qwertyui","yes")
        val username=PreferenceManager.getInstance(requireContext()).getEmail
        val userid=PreferenceManager.getInstance(requireContext()).getUserId
        mGetcaytegoryVm.getallFaqByCategory(username,position.toString(),userid.toString())
    }
}
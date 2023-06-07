package com.bb.gamingnews.ui.tearmandcondi

import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.TearmconditionFragmentBinding
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TandCFragment : BaseFragment<TearmconditionFragmentBinding>(){

    private val mPrivacyPolicyVM:TearmConditionVM by viewModel()
    override fun mLayoutRes(): Int {
        return R.layout.tearmcondition_fragment
    }

    override fun onViewReady() {

        mPrivacyPolicyVM.context=requireContext()
        val username=PreferenceManager.getInstance(requireContext()).getEmail
        val userId=PreferenceManager.getInstance(requireContext()).getUserId
        mPrivacyPolicyVM.getPrivacyPolicy(username,userId.toString())
        observer()
    }

    private fun observer() {

        mPrivacyPolicyVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mPrivacyPolicyVM.mSetPrivacyPolicyResponse.observe(this,
            {
                mBinding.containersTxt.setText(it.peekContent().data[0].desc)


            })

        mPrivacyPolicyVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.containersTxt, it)
        })

        //......................................SetCommunicationPrefrences.........................


    }
}
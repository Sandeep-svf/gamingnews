package com.bb.gamingnews.ui.privacypolicy

import android.view.View
import android.webkit.WebViewClient
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.PrivacyploicyFragmentBinding
import com.bb.gamingnews.extentions.showToast
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PrivacyPolicyFragment : BaseFragment<PrivacyploicyFragmentBinding>(){

    private var valuess:String=""
    private val mPrivacyPolicyVM:PrivacyPolicyVM by viewModel()
    override fun mLayoutRes(): Int {
        return R.layout.privacyploicy_fragment
    }

    override fun onViewReady() {

        mBinding.imageView40.setOnClickListener {

            activity?.onBackPressed()
        }
        mPrivacyPolicyVM.context=requireContext()
        valuess= requireArguments()!!.getString("value", "")

        //.........................webview...........................
        mBinding. webView.webViewClient = WebViewClient()
//        var ads= requireArguments()!!.getString("ads", "")

        // this will enable the javascript settings
        mBinding.webView.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        mBinding.webView.settings.setSupportZoom(true)
        //.......................................................


        if(valuess.equals("1"))
        {
            mBinding.toolbarlight.visibility=View.VISIBLE
            activity?.tv_title?.text = "Tearm and Condition"
            val username=PreferenceManager.getInstance(requireContext()).getEmail
            val userId=PreferenceManager.getInstance(requireContext()).getUserId
            mPrivacyPolicyVM.getTearmCondition(username,userId.toString())
        }
        else if(valuess.equals("2"))
        {
            mBinding.toolbarlight.visibility=View.VISIBLE
            activity?.tv_title?.text = "About GN 24*7"
            val username=PreferenceManager.getInstance(requireContext()).getEmail
            val userId=PreferenceManager.getInstance(requireContext()).getUserId
            mPrivacyPolicyVM.getTearmCondition(username,userId.toString())
        }
        else if(valuess.equals("3"))
        {mBinding.toolbarlight.visibility=View.VISIBLE
            activity?.tv_title?.text = "Contact And Support"
            val username=PreferenceManager.getInstance(requireContext()).getEmail
            val userId=PreferenceManager.getInstance(requireContext()).getUserId
            mPrivacyPolicyVM.getTearmCondition(username,userId.toString())
        }

        else if(valuess.equals("Login"))
        {
            mBinding.toolbarlight.visibility=View.VISIBLE

//            showToast("okkk",requireContext())
//            val username=PreferenceManager.getInstance(requireContext()).getEmail
//            val userId=PreferenceManager.getInstance(requireContext()).getUserId
            mPrivacyPolicyVM.getTearmCondition("","1")
            activity?.tv_title?.text = "Contact And Support"
        }
        else if(valuess.equals("4"))
        {mBinding.toolbarlight.visibility=View.GONE
            val username=PreferenceManager.getInstance(requireContext()).getEmail
            val userId=PreferenceManager.getInstance(requireContext()).getUserId
            mPrivacyPolicyVM.getTearmCondition(username,userId.toString())
            activity?.tv_title?.text = "Privacy Policy"
        }


        observer()
    }

    private fun observer() {

        mPrivacyPolicyVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mPrivacyPolicyVM.mTearmConditionResponse.observe(this,
            {

                if(valuess.equals("1"))
                {

                    mBinding. webView.loadUrl(it.data[0].termsAndCondition)

//                    mBinding.checkbox.isVisible=true
//                    mBinding.btnagree.isVisible=true
                    activity?.tv_title?.text = "Tearm and Condition"
//                    mBinding.containersTxt.setText(it.data[0].desc)
                }
                else if(valuess.equals("2"))
                {
                    mBinding. webView.loadUrl(it.data[1].termsAndCondition)

//                    mBinding.checkbox.isVisible=false
//                    mBinding.btnagree.isVisible=false
                    activity?.tv_title?.text = "About GN 24*7"
//                    mBinding.containersTxt.setText(it.data[1].desc)

                }
                else if(valuess.equals("3"))
                {
                    mBinding. webView.loadUrl(it.data[2].termsAndCondition)
//                    mBinding.checkbox.isVisible=false
//                    mBinding.btnagree.isVisible=false
                    activity?.tv_title?.text = "Contact And Support"
//                    mBinding.containersTxt.setText(it.data[2].desc)
                }
                else if(valuess.equals("Login"))
                {
                    mBinding. webView.loadUrl(it.data[2].termsAndCondition)
//                    mBinding.checkbox.isVisible=false
//                    mBinding.btnagree.isVisible=false
                    activity?.tv_title?.text = "Contact And Support"
//                    mBinding.containersTxt.setText(it.data[2].desc)
                }


                else if(valuess.equals("4"))
                {
                    if(it.data.size>=4)
                    {
                        mBinding. webView.loadUrl(it.data[3].termsAndCondition)
//                        mBinding.checkbox.isVisible=false
//                        mBinding.btnagree.isVisible=false
                        activity?.tv_title?.text = "Privacy Policy"
//                        mBinding.containersTxt.setText(it.data[3].desc)
                    }
                    else{
                        showToast("add privacy policy in api !")
                    }

                }




            })

        mPrivacyPolicyVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.imageView40, it)
        })

        //......................................SetCommunicationPrefrences.........................


    }
}
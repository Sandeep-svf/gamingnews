package com.bb.gamingnews.ui.dashboard.AdsPage

import android.webkit.WebViewClient
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.AdsDetailsPageBinding

class AdsDetailsFragment :BaseFragment<AdsDetailsPageBinding>() {
    override fun mLayoutRes(): Int {
        return  R.layout.ads_details_page
    }

    override fun onViewReady() {
       mBinding. webView.webViewClient = WebViewClient()
       var ads= requireArguments()!!.getString("ads", "")
        mBinding. webView.loadUrl(ads)

        // this will enable the javascript settings
        mBinding.webView.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        mBinding.webView.settings.setSupportZoom(true)
//
    }

}
package com.bb.gamingnews.ui.feed

import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentNewFeedBinding
import com.bb.gamingnews.ui.feed.interview.InterViewFragment


class NewFeedFragment : BaseFragment<FragmentNewFeedBinding>() {
    override fun mLayoutRes(): Int {
        return R.layout.fragment_new_feed
    }

    override fun onViewReady() {

        val newFragment: Fragment = FeedFragment()
        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.change_container, newFragment).commit()
            mBinding.radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, radioButtonID ->
            when (radioButtonID) {
                R.id.news -> {
                    val newFragment: Fragment = FeedFragment()
                    val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
                    transaction.replace(R.id.change_container, newFragment).commit()

                }
                R.id.interview -> {
                    val interviewFragment: Fragment = InterViewFragment()
                    val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
                    transaction.replace(R.id.change_container, interviewFragment).commit()

                }
            }
        })
    }


}
package com.bb.gamingnews.ui.teammemberprofile

import androidx.recyclerview.widget.LinearLayoutManager
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.FragmentTeamMemberProfileBinding

class TeamMemberProfileFragment : BaseFragment<FragmentTeamMemberProfileBinding>() {
    override fun mLayoutRes(): Int {
        return R.layout.fragment_team_member_profile
    }

    override fun onViewReady() {
        mBinding.recyclerArticle.layoutManager = LinearLayoutManager(requireContext())
//        mBinding.recyclerArticle.adapter = MemberArticleAdapter()


    }


}
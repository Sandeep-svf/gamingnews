package com.bb.gamingnews.ui.Comments

import android.util.Log
import com.bb.gamingnews.R
import com.bb.gamingnews.base.BaseFragment
import com.bb.gamingnews.databinding.CommentRagmentBinding
import com.bb.gamingnews.extentions.showToast
import com.bb.gamingnews.ui.Comments.adapter.CommentAdapter
import com.bb.gamingnews.utils.ErrorUtil
import com.bb.gamingnews.utils.PreferenceManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class CommentFragmant: BaseFragment<CommentRagmentBinding>() {

    private var  articalId:String=""
    private val mCoommentVM:CommnetsVM by viewModel()
    override fun mLayoutRes(): Int {
        return R.layout.comment_ragment
    }

    override fun onViewReady() {

        mCoommentVM.context=requireContext()
        if(arguments!=null)
        {
            val heading = requireArguments()!!.getString("heading", "")
            articalId = requireArguments()!!.getString("articalId", "")
            mBinding.txtHeading.setText("Title: "+heading)
        }

        val username= PreferenceManager.getInstance(requireContext()).getEmail
        val userId= PreferenceManager.getInstance(requireContext()).getUserId
        mCoommentVM.getArticalComment(username,articalId,userId.toString())
        mBinding.imagesend.setOnClickListener {

            if(mBinding.editchat.text.toString().isNullOrBlank())
            {
             showToast("Enter your comments !")
            }
            else
            {
                var comment=mBinding.editchat.text.toString()
                mCoommentVM.postArticalComment(username,articalId,comment,userId.toString())
            }

        }


        observer()
    }
    private fun observer() {

        mCoommentVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mCoommentVM.mCommentResponse.observe(this,
            {

                Log.v("rtysccccuio","pp: "+it.peekContent().data.size.toString())
                mBinding.recylerComment.adapter= CommentAdapter(requireContext(),it.peekContent().data)

            })

        mCoommentVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.recylerComment, it)
        })

        //......................................PostComment.........................
        mCoommentVM.progressIndicator.observe(this,  {
//            toggleLoader(requireContext(), it)
        })
        mCoommentVM.mPostCommentResponse.observe(this,
            {
                mBinding.editchat.setText("")
                val username= PreferenceManager.getInstance(requireContext()).getEmail
                val userId= PreferenceManager.getInstance(requireContext()).getUserId
                mCoommentVM.getArticalComment(username,articalId,userId.toString())

            })

        mCoommentVM.errorResponse.observe(this, {
            ErrorUtil.handlerGeneralError(requireContext(), mBinding.recylerComment, it)
        })


    }
}